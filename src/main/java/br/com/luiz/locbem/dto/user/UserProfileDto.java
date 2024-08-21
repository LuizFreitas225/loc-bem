package br.com.luiz.locbem.dto.user;

import br.com.luiz.locbem.model.user.Perfil;
import br.com.luiz.locbem.model.user.Status;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserProfileDto {
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private String name;
    private String email;
    private String personRegistration;
    private Boolean isNaturalPerson;
    private String cep;
    private String longitude;
    private String latitude;
    private Status status = Status.ACTIVE;
    private Perfil perfil = Perfil.USER;
}
