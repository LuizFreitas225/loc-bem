package br.com.luiz.locbem.user;

import br.com.luiz.locbem.model.user.Status;
import br.com.luiz.locbem.model.user.User;

import java.time.LocalDateTime;

public final class UserMockUtil {
    public  static User getUserWithId(){
        User user = new User();
        user.setId((long) 1);
        user.setName("Luiz");
        user.setPassword("senha1");
        user.setStatus(Status.ACTIVE);
        user.setEmail("luiz@atech.com");
        user.setCreateDate(LocalDateTime.now());
        user.setLastModifiedDate(LocalDateTime.now());

        return user;
    }

    public  static User getUserWithoutId(){
        User user = new User();
        user.setName("Luiz");
        user.setPassword("senha1");
        user.setStatus(Status.ACTIVE);
        user.setEmail("luiz@atech.com.br");
        user.setCreateDate(LocalDateTime.now());
        user.setLastModifiedDate(LocalDateTime.now());

        return user;
    }

}
