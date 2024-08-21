package br.com.luiz.locbem.dto.user;

import br.com.luiz.locbem.constant.ErrorMessage;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class UserDto {
    private String name;
    private String email;
    private String cep;
    private String longitude;
    private String latitude;
}
