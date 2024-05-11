package br.com.luiz.locbem.service;

import br.com.luiz.locbem.dto.oferta.OfertaDTO;
import br.com.luiz.locbem.model.offer.*;
import br.com.luiz.locbem.model.user.PreferenciaUsuario;
import br.com.luiz.locbem.service.criterio.CriterioService;
import br.com.luiz.locbem.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AHPService {
    private final ModelMapper modelMapper;


    public List<OfertaComPontuacao> calcularPontuacao(PreferenciaUsuario preferencias, List<Oferta> ofertas) {

        // Definir pesos normalizados
        List<OfertaComPontuacao> ofertasComPontuacaoList = ModelMapperUtil.toOfertaComPontuacao(ofertas, modelMapper);
        CriterioService.normalizarTodosCriterios(preferencias, ofertasComPontuacaoList);
        Map<String, Double> pesos = getPesos(preferencias);

        for (int i = 0; i < ofertasComPontuacaoList.size(); i++) {
            OfertaComPontuacao oferta = ofertasComPontuacaoList.get(i);
            OfertaNormalizada ofertaNormalizada =  ofertasComPontuacaoList.get(i).getOfertaNormalizada();

            double pontuacao =
                    (ofertaNormalizada.getPreco() * pesos.get("preco")) +
                            (ofertaNormalizada.getQuilometragem() * pesos.get("quilometragem")) +
                            (ofertaNormalizada.getTipoVeiculo() * pesos.get("tipoVeiculo")) +
                            (ofertaNormalizada.getCombustivel() * pesos.get("combustivel")) +
                            (ofertaNormalizada.getTipoVeiculo() * pesos.get("estadoVeiculo")) +
                            (ofertaNormalizada.getCaracteristicas() * pesos.get("caracteristicas"));

            ofertasComPontuacaoList.get(i).setPontuacao(pontuacao);
        }

        return ofertasComPontuacaoList;
    }



    private Map<String, Double> getPesos(PreferenciaUsuario preferencias) {
        Map<String, Double> pesosNormalizados = new HashMap<>();
        int somaPesos = preferencias.getPesoPreco() + preferencias.getPesoQuilometragem() +
              + preferencias.getPesoTipoVeiculo() +
                preferencias.getPesoCombustivel() + preferencias.getPesoEstadoVeiculo();

        pesosNormalizados.put("preco", (double) preferencias.getPesoPreco() / somaPesos);
        pesosNormalizados.put("quilometragem", (double) preferencias.getPesoQuilometragem() / somaPesos);
        pesosNormalizados.put("tipoVeiculo", (double) preferencias.getPesoTipoVeiculo() / somaPesos);
        pesosNormalizados.put("combustivel", (double) preferencias.getPesoCombustivel() / somaPesos);
        pesosNormalizados.put("estadoVeiculo", (double) preferencias.getPesoEstadoVeiculo() / somaPesos);
        pesosNormalizados.put("caracteristicas", (double) preferencias.getPesoEstadoVeiculo() / somaPesos);

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
