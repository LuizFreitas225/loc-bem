package br.com.luiz.locbem.service;

import br.com.luiz.locbem.dto.oferta.OfertaDTO;
import br.com.luiz.locbem.model.offer.*;
import br.com.luiz.locbem.model.user.PreferenciaUsuario;
import br.com.luiz.locbem.service.criterio.CriterioService;
import br.com.luiz.locbem.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AHPService {
    private final ModelMapper modelMapper;


    public List<OfertaComPontuacao> calcularPontuacao(PreferenciaUsuario preferencias, List<Oferta> ofertas) {

        // Definir pesos normalizados

          List<OfertaComPontuacao> ofertaComPontuacaoList = ModelMapperUtil.toOfertaComPontuacao(ofertas, modelMapper);

        if( preferencias == null ){
            return ofertaComPontuacaoList;
        }
        CriterioService.normalizarTodosCriterios(preferencias, ofertaComPontuacaoList);
        Map<String, Double> pesos = getPesos(preferencias);

        for (int i = 0; i < ofertaComPontuacaoList.size(); i++) {
            OfertaComPontuacao oferta = ofertaComPontuacaoList.get(i);
            OfertaNormalizada ofertaNormalizada =  ofertaComPontuacaoList.get(i).getOfertaNormalizada();

            double pontuacao =
                    (ofertaNormalizada.getPreco() * pesos.get("preco")) +
                            (ofertaNormalizada.getQuilometragem() * pesos.get("quilometragem")) +
                            (ofertaNormalizada.getTipoVeiculo() * pesos.get("tipoVeiculo")) +
                            (ofertaNormalizada.getCombustivel() * pesos.get("combustivel")) +
                            (ofertaNormalizada.getEstadoVeiculo() * pesos.get("estadoVeiculo")) +
                            (ofertaNormalizada.getCaracteristicas() * pesos.get("caracteristicas"));

            ofertaComPontuacaoList.get(i).setPontuacao(pontuacao);
        }

        return ofertaComPontuacaoList;
    }

    private Map<String, Double> getPesos(PreferenciaUsuario preferencias) {
        Map<String, Double> pesosNormalizados = new HashMap<>();
        int somaPesos = preferencias.getPesoPreco() + preferencias.getPesoQuilometragem() +
              + preferencias.getPesoTipoVeiculo() +
                preferencias.getPesoCombustivel() + preferencias.getPesoEstadoVeiculo() + preferencias.getPesoCaracteristicas();

        pesosNormalizados.put("preco", (double) preferencias.getPesoPreco() / somaPesos);
        pesosNormalizados.put("quilometragem", (double) preferencias.getPesoQuilometragem() / somaPesos);
        pesosNormalizados.put("tipoVeiculo", (double) preferencias.getPesoTipoVeiculo() / somaPesos);
        pesosNormalizados.put("combustivel", (double) preferencias.getPesoCombustivel() / somaPesos);
        pesosNormalizados.put("estadoVeiculo", (double) preferencias.getPesoEstadoVeiculo() / somaPesos);
        pesosNormalizados.put("caracteristicas", (double) preferencias.getPesoCaracteristicas() / somaPesos);

        return pesosNormalizados;
    }
}
