package br.com.luiz.locbem.model.offer;

import lombok.Data;

import java.util.List;


@Data
public class OfertaComPontuacao {

    private Long id;
    private String modelo;
    private int anoFabricacao;
    private int anoModelo;
    private double preco;
    private String descricao;
    private double quilometragem;
    private String coordenadas;
    private int userId;
    private List<String> imagens;
    private TipoVeiculo tipoVeiculo;
    private Combustivel combustivel;
    private EstadoVeiculo estadoVeiculo;
    private List<CaracteristicaVeiculo> caracteristica;

    private double pontuacao;
    private OfertaNormalizada  ofertaNormalizada = new OfertaNormalizada();

}
