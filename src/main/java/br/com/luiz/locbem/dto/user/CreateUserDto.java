package br.com.luiz.locbem.dto.user;

import br.com.luiz.locbem.constant.ErrorMessage;
import br.com.luiz.locbem.model.user.Perfil;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CreateUserDto { 
    @NotBlank(message = ErrorMessage.NAME_IS_MANDATORY)
    private String name;
    @NotBlank(message = ErrorMessage.EMAIL_IS_MANDATORY)
    @Email(message = ErrorMessage.VALID_EMAIL_IS_REQUIRED)
    private String email;
    @NotBlank(message = ErrorMessage.PASSWORD_IS_MANDATORY)
    private String password;
    @NotBlank(message = ErrorMessage.PERSON_REGISTRATION_IS_EMPTY)
    private String personRegistration;
    private Boolean isNaturalPerson = true;
    @NotBlank(message = ErrorMessage.CEP_IS_MANDATORY)
    @Pattern(regexp = "\\d{5}-\\d{3}", message = ErrorMessage.CEP_IS_INVALID)
    private String cep;
    @NotBlank(message = ErrorMessage.LONGITUDE_IS_MANDATORY)
    private String longitude;
    @NotBlank(message = ErrorMessage.LATITUDE_IS_MANDATORY)
    private String latitude;
    private Perfil perfil = Perfil.USER;
}
