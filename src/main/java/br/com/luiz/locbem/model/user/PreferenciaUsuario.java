package br.com.luiz.locbem.model.user;


import br.com.luiz.locbem.dto.georeferencing.CoordinatesDTO;
import br.com.luiz.locbem.model.offer.CaracteristicaVeiculo;
import br.com.luiz.locbem.model.offer.Combustivel;
import br.com.luiz.locbem.model.offer.EstadoVeiculo;
import br.com.luiz.locbem.model.offer.TipoVeiculo;

import lombok.Data;

import javax.validation.constraints.Min;
import java.util.List;

@Data
public class PreferenciaUsuario {
    @Min(value = 1, message = "O pesoPreco deve ser maior ou igual a 1.")
    private int pesoPreco;
    @Min(1)
    private int pesoQuilometragem;
    @Min(value = 1, message = "O pesoTipoVeiculo deve ser maior ou igual a 1.")
    private int pesoTipoVeiculo;
    private List<TipoVeiculo> tipoVeiculo;
    @Min(value = 1, message = "O pesoCombustivel deve ser maior ou igual a 1.")
    private int pesoCombustivel;
    private List<Combustivel> combustivel;
    @Min(value = 1, message = "O pesoEstadoVeiculo deve ser maior ou igual a 1.")
    private int pesoEstadoVeiculo;
    private List<EstadoVeiculo> estadoVeiculo;
    @Min(value = 1, message = "O pesoCaracteristicas deve ser maior ou igual a 1.")
    private int pesoCaracteristicas;
    private List<CaracteristicaVeiculo> caracteristicas;
    @Min(value = 1, message = "A distanciaMaxima deve ser maior ou igual a 1.")
    private int distanciaMaxima;
    CoordinatesDTO coordenadasUsuario;


}
