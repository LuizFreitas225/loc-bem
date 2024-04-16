package br.com.luiz.locbem.dto.user;

import br.com.luiz.locbem.model.user.Perfil;
import br.com.luiz.locbem.model.user.Status;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserDto {
    private String name;
    private String email;
    private String cep;
    private String coordinateY;
    private String coordinateX;
}
