package br.com.luiz.locbem.dto.oferta;

import br.com.luiz.locbem.model.offer.CaracteristicaVeiculo;
import br.com.luiz.locbem.model.offer.Combustivel;
import br.com.luiz.locbem.model.offer.EstadoVeiculo;
import br.com.luiz.locbem.model.offer.TipoVeiculo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
public class CreateOfertaDTO {
    @NotBlank(message = "O modelo não pode estar em branco")
    private String modelo;

    @NotNull(message = "O ano de fabricação não pode ser nulo")
    private int anoFabricacao;

    @NotNull(message = "O ano do modelo não pode ser nulo")
    private int anoModelo;

    @NotNull(message = "O preço não pode ser nulo")
    private double preco;

    @NotBlank(message = "A descrição não pode estar em branco")
    private String descricao;

    @NotNull(message = "A quilometragem não pode ser nula")
    private double quilometragem;

    @NotBlank(message = "A condição não pode estar em branco")
    private String condicao;

    @NotNull(message = "A latitude não pode ser nula")
    private Double latitude;

    @NotNull(message = "A longitude não pode ser nula")
    private Double longitude;

    @NotEmpty(message = "A lista de imagens não pode estar vazia")
    private List<String> imagens;

    @NotNull(message = "O tipo de veículo não pode ser nulo")
    private TipoVeiculo tipoVeiculo;

    @NotNull(message = "O combustível não pode ser nulo")
    private Combustivel combustivel;

    @NotNull(message = "O estado do veículo não pode ser nulo")
    private EstadoVeiculo estadoVeiculo;

    @NotEmpty(message = "A lista de características não pode estar vazia")
    private List<CaracteristicaVeiculo> caracteristica;

}
