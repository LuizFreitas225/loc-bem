package br.com.luiz.locbem.service;

import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.user.PreferenciaUsuario;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AHPService {


    public double calcularPontuacao(PreferenciaUsuario preferencias, Oferta oferta) {
        // Definir pesos normalizados
        Map<String, Double> pesosNormalizados = normalizarPesos(preferencias);

        // Calcular pontuação usando pesos normalizados
        double pontuacao =
                (oferta.getPreco() * pesosNormalizados.get("preco")) +
                        (oferta.getQuilometragem() * pesosNormalizados.get("quilometragem")) +
                        (pesosNormalizados.get("condicao") * calcularPontuacaoCondicao()) +
                        (pesosNormalizados.get("tipoVeiculo") * calcularPontuacaoTipoVeiculo()) +
                        (pesosNormalizados.get("combustivel") * calcularPontuacaoCombustivel()) +
                        (pesosNormalizados.get("estadoVeiculo") * calcularPontuacaoEstadoVeiculo());

        return pontuacao;
    }

    private Map<String, Double> normalizarPesos(PreferenciaUsuario preferencias) {
        Map<String, Double> pesosNormalizados = new HashMap<>();
        int somaPesos = preferencias.getPesoPreco() + preferencias.getPesoQuilometragem() +
                preferencias.getPesoCondicao() + preferencias.getPesoTipoVeiculo() +
                preferencias.getPesoCombustivel() + preferencias.getPesoEstadoVeiculo();

        pesosNormalizados.put("preco", (double) preferencias.getPesoPreco() / somaPesos);
        pesosNormalizados.put("quilometragem", (double) preferencias.getPesoQuilometragem() / somaPesos);
        pesosNormalizados.put("condicao", (double) preferencias.getPesoCondicao() / somaPesos);
        pesosNormalizados.put("tipoVeiculo", (double) preferencias.getPesoTipoVeiculo() / somaPesos);
        pesosNormalizados.put("combustivel", (double) preferencias.getPesoCombustivel() / somaPesos);
        pesosNormalizados.put("estadoVeiculo", (double) preferencias.getPesoEstadoVeiculo() / somaPesos);

        return pesosNormalizados;
    }

    private double calcularPontuacaoCondicao() {
        // Lógica para calcular a pontuação da condição do veículo
        return 0.0; // Substitua 0.0 pelo valor calculado
    }

    private double calcularPontuacaoTipoVeiculo() {
        // Lógica para calcular a pontuação do tipo de veículo
        return 0.0; // Substitua 0.0 pelo valor calculado
    }

    private double calcularPontuacaoCombustivel() {
        // Lógica para calcular a pontuação do combustível
        return 0.0; // Substitua 0.0 pelo valor calculado
    }

    private double calcularPontuacaoEstadoVeiculo() {
        // Lógica para calcular a pontuação do estado do veículo
        return 0.0; // Substitua 0.0 pelo valor calculado
    }

}
