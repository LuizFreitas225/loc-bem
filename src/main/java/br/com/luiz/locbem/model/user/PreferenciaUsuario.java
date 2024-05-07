package br.com.luiz.locbem.model.user;


import lombok.Data;

@Data
public class PreferenciaUsuario {
    private int pesoPreco;
    private int pesoQuilometragem;
    private int pesoCondicao;
    private int pesoTipoVeiculo;
    private int pesoCombustivel;
    private int pesoEstadoVeiculo;
}
