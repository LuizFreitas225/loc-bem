package br.com.luiz.locbem.model.offer;


import lombok.Data;

@Data
public class OfertaNormalizada {
    private double preco;
    private double quilometragem;
    private double condicao;
    private double tipoVeiculo;
    private double combustivel;
    private double eEstadoVeiculo;
}
