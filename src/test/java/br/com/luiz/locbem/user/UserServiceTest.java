//package br.com.luiz.locbem.user;
//
//import br.com.luiz.locbem.exception.EmailInUseException;
//import br.com.luiz.locbem.exception.ShortPasswordException;
//import br.com.luiz.locbem.exception.UserIsDeletedException;
//import br.com.luiz.locbem.exception.UserIsInactiveException;
//import br.com.luiz.locbem.model.user.Status;
//import br.com.luiz.locbem.model.user.User;
//import br.com.luiz.locbem.repository.UserRepository;
//import br.com.luiz.locbem.service.UserService;
//import br.com.luiz.locbem.util.PaginationUtil;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//
//import org.mockito.Mock;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//@ExtendWith(SpringExtension.class)
//public class UserServiceTest {
//    @InjectMocks
//    UserService userService;
//
//    @Mock
//    UserRepository userRepository;
//
//    static  User fullUser;
//
//    static User incompleteUser;
//
//    @BeforeEach
//    public  void load(){
//        fullUser = UserMockUtil.getUserWithId();
//        incompleteUser = UserMockUtil.getUserWithoutId();
//    }
//
//    @Test
//    void create_doNotCreateIfStatusIsDeleted() {
//        fullUser.setStatus(Status.DELETED);
//        incompleteUser.setStatus(Status.DELETED);
//
//        BDDMockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(fullUser);
//
//        Assertions.assertThatThrownBy(() -> userService.create(incompleteUser)).isInstanceOf(UserIsDeletedException.class);
//    }
//
//    @Test
//    void create_doNotCreateIfPasswordIsShort() {
//        fullUser.setPassword("senha");
//        incompleteUser.setPassword("senha");
//
//        BDDMockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(fullUser);
//
//        Assertions.assertThatThrownBy(() -> userService.create(incompleteUser)).isInstanceOf(ShortPasswordException.class);
//    }
//
//    @Test
//    void create_doNotCreateIfEmailIsInUse() {
//
//        BDDMockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(fullUser);
//        BDDMockito.when(userRepository.existsByEmail(ArgumentMatchers.any(String.class))).thenReturn(true);
//
//        Assertions.assertThatThrownBy(() -> userService.create(incompleteUser)).isInstanceOf(EmailInUseException.class);
//    }
//
//    @Test
//    void update_doNotCreateIfStatusIsDeleted() {
//        fullUser.setStatus(Status.DELETED);
//
//        BDDMockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(fullUser);
//        BDDMockito.when(userRepository.findByEmail((ArgumentMatchers.any(String.class)))).thenReturn(fullUser);
//
//        Assertions.assertThatThrownBy(() -> userService.update(fullUser)).isInstanceOf(UserIsDeletedException.class);
//    }
//
//    @Test
//    void update_doNotCreateIfPasswordIsShort() {
//        fullUser.setPassword("senha");
//
//        BDDMockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(fullUser);
//        BDDMockito.when(userRepository.findByEmail((ArgumentMatchers.any(String.class)))).thenReturn(fullUser);
//
//        Assertions.assertThatThrownBy(() -> userService.update(fullUser)).isInstanceOf(ShortPasswordException.class);
//    }
//
//    @Test
//    void update_doNotCreateIfEmailIsInUse() {
//        incompleteUser.setEmail("atech@gmial.com.br");
//
//        BDDMockito.when(userRepository.findByEmail(ArgumentMatchers.any(String.class))).thenReturn(fullUser);
//        BDDMockito.when(userRepository.existsByEmail(ArgumentMatchers.any(String.class))).thenReturn(true);
//        incompleteUser.setId(fullUser.getId());
//        Assertions.assertThatThrownBy(() -> userService.update(incompleteUser)).isInstanceOf(EmailInUseException.class);
//    }
//
//    @Test
//    void findAByNameOrCode_CannotIsEmptyAndCannotHaveListChange() {
//        BDDMockito.when(userRepository.findAByNameOrEmailOrUserName(ArgumentMatchers.any(PageRequest.class),
//                ArgumentMatchers.anyString())).thenReturn(new PageImpl<>(List.of(fullUser)));
//
//        Page<User> page = userService.findAByNameOrEmailOrUserName(PaginationUtil.configuringPageable(null, null,
//                null, null), "");
//
//        Assertions.assertThat(page).isNotEmpty();
//        Assertions.assertThat(page.toList()).isNotEmpty();
//        Assertions.assertThat(page.toList().size()).isEqualTo(1);
//        Assertions.assertThat(page.toList().get(0)).isEqualTo(fullUser);
//    }
//
//    @Test
//    void findValidUserByEmail_CannotIsEmpty() {
//        BDDMockito.when(userRepository.findByEmail(ArgumentMatchers.any(String.class))).thenReturn(fullUser);
//
//        User user = userService.findValidUserByEmail("luiz@atech.com");
//
//        Assertions.assertThat(user).isNotNull();
//        Assertions.assertThat(user).isEqualTo(fullUser);
//    }
//
//    @Test
//    void findValidUserByEmail_cannotIsDeleted() {
//        fullUser.setStatus(Status.DELETED);
//        BDDMockito.when(userRepository.findByEmail(ArgumentMatchers.any(String.class))).thenReturn(fullUser);
//
//        Assertions.assertThatThrownBy(() -> userService.findValidUserByEmail("luiz@atech.com")).isInstanceOf(UserIsDeletedException.class);
//    }
//    @Test
//    void findValidUserByEmail_cannotIsInactive() {
//        fullUser.setStatus(Status.INACTIVE);
//        BDDMockito.when(userRepository.findByEmail(ArgumentMatchers.any(String.class))).thenReturn(fullUser);
//
//        Assertions.assertThatThrownBy(() -> userService.findValidUserByEmail("luiz@atech.com")).isInstanceOf(UserIsInactiveException.class);
//    }
//}
