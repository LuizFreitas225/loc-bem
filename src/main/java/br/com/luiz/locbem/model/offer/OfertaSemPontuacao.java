package br.com.luiz.locbem.model.offer;

import lombok.Data;

import java.util.List;


@Data
public class OfertaSemPontuacao {

    private Long id;
    private String modelo;
    private int anoFabricacao;
    private int anoModelo;
    private double preco;
    private String descricao;
    private double quilometragem;
    private Double latitude;
    private Double longitude;
    private int userId;
    private List<String> linkImagens;
    private TipoVeiculo tipoVeiculo;
    private Combustivel combustivel;
    private EstadoVeiculo estadoVeiculo;
    private List<CaracteristicaVeiculo> caracteristica;
    private double distanciaEmKM;

}
