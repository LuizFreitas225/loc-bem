package br.com.luiz.locbem.dto;

import br.com.luiz.locbem.constant.ErrorMessage;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class EditUserDto {
    @NotNull(message = ErrorMessage.ID_IS_MANDATORY)
    private Long id;
    @NotBlank(message = ErrorMessage.NAME_IS_MANDATORY)
    private String name;
    @NotBlank(message = ErrorMessage.EMAIL_IS_MANDATORY)
    @Email(message = ErrorMessage.VALID_EMAIL_IS_REQUIRED)
    private String email;
    @NotBlank(message = ErrorMessage.PASSWORD_IS_MANDATORY)
    private String password;
    @NotBlank(message = ErrorMessage.CPF_IS_MANDATORY)
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = ErrorMessage.CPF_IS_INVALID)
    private String cpf;
    @NotBlank(message = ErrorMessage.CEP_IS_MANDATORY)
    @Pattern(regexp = "\\d{5}-\\d{3}", message = ErrorMessage.CEP_IS_INVALID)
    private String cep;
    @NotBlank(message = ErrorMessage.COORDINATE_IS_MANDATORY)
    private String coordinateY;
    @NotBlank(message = ErrorMessage.COORDINATE_IS_MANDATORY)
    private String coordinateX;
}