package br.com.luiz.locbem.dto.oferta;

import br.com.luiz.locbem.model.offer.CaracteristicaVeiculo;
import br.com.luiz.locbem.model.offer.Combustivel;
import br.com.luiz.locbem.model.offer.EstadoVeiculo;
import br.com.luiz.locbem.model.offer.TipoVeiculo;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

public class CreateOfertaDTO {
    private String modelo;
    private int anoFabricacao;
    private int anoModelo;
    private double preco;
    private String descricao;
    private double quilometragem;
    private String condicao;
    private String coordenadas;

    private List<String> imagens;

    private TipoVeiculo tipoVeiculo;


    private Combustivel combustivel;


    private EstadoVeiculo estadoVeiculo;

    private List<CaracteristicaVeiculo> caracteristicas;
}
