package br.com.luiz.locbem.dto;

import br.com.luiz.locbem.model.Perfil;
import br.com.luiz.locbem.model.Status;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;


@Data
public class UserProfileDto {
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private String name;
    private String email;
    private String password;
    private String personRegistration;
    private Boolean isNaturalPerson;
    private String cep;
    private String coordinateY;
    private String coordinateX;
    private Status status = Status.ACTIVE;
    private Perfil perfil = Perfil.USER;
}
