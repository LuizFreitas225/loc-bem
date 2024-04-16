package br.com.luiz.locbem.service;

import br.com.luiz.locbem.exception.*;
import br.com.luiz.locbem.model.user.Status;
import br.com.luiz.locbem.model.user.User;
import br.com.luiz.locbem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private static final int MAX_PASSWORD_SIZE_ALLOWED = 6;


    public User create(final User user) {
        log.info("UserService.create - start - input  [{}]", user.getEmail());

        user.setPersonRegistration( removeSpecialCharacters(user.getPersonRegistration() ));
        validateCreateUser(user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User userCreated = userRepository.save(user);

        log.info("UserService.create - end- output [{}]", user.getEmail());
        return userCreated;
    }

    public User findAndValidateById(final long id) {
        log.info("UserService.findAndValidateById - start - input [{}]", id);

        User userFound = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        log.info("UserService.findAndValidateById - end - output [{}]", userFound.getId());
        return userFound;
    }

    public void delete(final long id) {
        log.info("UserService.findAndValidateById - start - input [{}]", id);

        User userFound = findAndValidateById(id);
        userFound.setStatus(Status.DELETED);
        userRepository.save(userFound);
    }

    public User update(final User user) {
        log.info("UserService.update - start - input  [{},{}]", user.getEmail(), user.getId());

        user.setPersonRegistration( removeSpecialCharacters(user.getPersonRegistration() ));

        User currentUser = this.findValidUserByEmail(user.getEmail());
        user.setCreateDate(currentUser.getCreateDate());
        validateEditUser(currentUser, user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User userCreated = userRepository.save(user);

        log.info("UserService.update - end- output [{},{}]", userCreated.getEmail(), userCreated.getId());
        return userCreated;
    }

    public Page<User> findAByNameOrEmailOrUserName(PageRequest pageRequest, String searchTerm) {
        log.info("UserService.findAByNameOrEmailOrUserName - start - input [{}]", searchTerm);
        Page<User> userPage = userRepository.findAByNameOrEmailOrUserName(pageRequest, searchTerm);
        log.info("UserService.findAByNameOrEmailOrUserName - end - output [{}]", userPage.getTotalElements());
        return userPage;
    }
    public User findValidUserByEmail(String email) {
        log.info("UserService.findByEmail - start - input [{}]", email);
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        if (user.getStatus() == Status.DELETED) {
           throw new UserIsDeletedException();
        }

        if (user.getStatus() == Status.INACTIVE) {
            throw new UserIsInactiveException();
        }
        return user;
    }

    public User getUserAuthenticated(){
        return findValidUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    private void validateCreateUser(User user) {
        log.info("UserService.validateUser - start - input [{}]", user.getEmail());
        if (validateDeletedUser(user)) {
            throw new UserIsDeletedException();
        }
        if (!passwordIsValid(user.getPassword())) {
            throw new ShortPasswordException();
        }
        if (emailInUse(user.getEmail())) {
            throw new EmailInUseException();
        }
        validPersonRegistration(user);
    }

    private void validateEditUser(User currentUser, User newUser) {
        log.info("UserService.validateEditUser - start - input [{}]", currentUser.getEmail(), newUser.getEmail());
        if (validateDeletedUser(currentUser)) {
            throw new UserIsDeletedException();
        }

        if (!passwordIsValid(newUser.getPassword())) {
            throw new ShortPasswordException();
        }

        if (!currentUser.getEmail().equals(newUser.getEmail()) && emailInUse(newUser.getEmail())) {
            throw new EmailInUseException();
        }
        validPersonRegistration(newUser);

    }
    private boolean validateDeletedUser(User user) {
        return (user.getStatus() == Status.DELETED);
    }

    private void validPersonRegistration(User user) {
        if( personRegistrationInUse(user.getPersonRegistration())){
            throw new PersonRegistrationInUseException();
        }

        if(user.getIsNaturalPerson()) {
            if(!validarCPF(user.getPersonRegistration())){
                throw new PersonRegistrationInvalidException();
            }
        }else{
            if(!validarCNPJ(user.getPersonRegistration())){
                throw new PersonRegistrationInvalidException();
            }
        }


    }

    private boolean emailInUse(final String email) {
        return  userRepository.existsByEmail(email);
    }

    private boolean personRegistrationInUse(final String personRegistration) {
        return  userRepository.existsByPersonRegistration(personRegistration);
    }

    private boolean passwordIsValid(final String password) {
        return  password.length() >= MAX_PASSWORD_SIZE_ALLOWED;
    }


    private static boolean validarCPF(String cpf) {
        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11)
            return false;

        // Verifica se todos os dígitos são iguais, o que tornaria o CPF inválido
        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int resto = soma % 11;
        int digitoVerificador1 = resto < 2 ? 0 : 11 - resto;

        // Verifica se o primeiro dígito verificador está correto
        if (digitoVerificador1 != Character.getNumericValue(cpf.charAt(9)))
            return false;

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        int digitoVerificador2 = resto < 2 ? 0 : 11 - resto;

        // Verifica se o segundo dígito verificador está correto
        return digitoVerificador2 == Character.getNumericValue(cpf.charAt(10));
    }

    private static boolean validarCNPJ(String cnpj) {

        // Verifica se o CNPJ possui 14 dígitos
        if (cnpj.length() != 14)
            return false;

        // Calcula o primeiro dígito verificador
        int soma = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso++;
            if (peso == 10)
                peso = 2;
        }
        int resto = soma % 11;
        int digitoVerificador1 = resto < 2 ? 0 : 11 - resto;

        // Verifica se o primeiro dígito verificador está correto
        if (digitoVerificador1 != Character.getNumericValue(cnpj.charAt(12)))
            return false;

        // Calcula o segundo dígito verificador
        soma = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso++;
            if (peso == 10)
                peso = 2;
        }
        resto = soma % 11;
        int digitoVerificador2 = resto < 2 ? 0 : 11 - resto;

        // Verifica se o segundo dígito verificador está correto
        return digitoVerificador2 == Character.getNumericValue(cnpj.charAt(13));
    }
    private String removeSpecialCharacters( String text) {
        return text = text.replaceAll("[^0-9]", "");
    }
}
