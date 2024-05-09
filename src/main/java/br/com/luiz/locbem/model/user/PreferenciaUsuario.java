package br.com.luiz.locbem.model.user;


import br.com.luiz.locbem.model.offer.CaracteristicaVeiculo;
import br.com.luiz.locbem.model.offer.Combustivel;
import br.com.luiz.locbem.model.offer.EstadoVeiculo;
import br.com.luiz.locbem.model.offer.TipoVeiculo;
import lombok.Data;

import java.util.List;

@Data
public class PreferenciaUsuario {
    private int pesoPreco;
    private int pesoQuilometragem;
    private int pesoTipoVeiculo;
    private List<TipoVeiculo> tipoVeiculo;
    private int pesoCombustivel;
    private List<Combustivel> combustivel;
    private int pesoEstadoVeiculo;
    private List<EstadoVeiculo> estadoVeiculo;
    private int pesoCaracteristicas;
    private List<CaracteristicaVeiculo> caracteristicas;

}
