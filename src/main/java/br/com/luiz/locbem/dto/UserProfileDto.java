package br.com.luiz.locbem.dto;

import br.com.luiz.locbem.model.Perfil;
import br.com.luiz.locbem.model.Status;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class UserProfileDto {
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String cep;
    private String coordinateY;
    private String coordinateX;
    private Status status;
    private Perfil perfil;
}
