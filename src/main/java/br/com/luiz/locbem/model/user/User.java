package br.com.luiz.locbem.model.user;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;


@Entity
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String personRegistration = null;
    private Boolean isNaturalPerson = true;
    private String cep;
    private String coordinateY;
    private String coordinateX;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Enumerated(EnumType.STRING)
    private Perfil perfil = Perfil.USER;
}
