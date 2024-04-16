package br.com.luiz.locbem.dto.oferta;

import br.com.luiz.locbem.dto.user.UserDto;
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
public class OfertaDTO {
    private Long id;
    private String modelo;
    private int anoFabricacao;
    private int anoModelo;
    private double preco;
    private String descricao;
    private double quilometragem;
    private String condicao;
    private String coordenadas;
    private UserDto user;
    private List<String> imagens;
    private TipoVeiculo tipoVeiculo;
    private Combustivel combustivel;
    private EstadoVeiculo estadoVeiculo;
    private List<CaracteristicaVeiculo> caracteristicas;

}
