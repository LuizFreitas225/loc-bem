package br.com.luiz.locbem.service.criterio;

import br.com.luiz.locbem.model.offer.OfertaComPontuacao;

import java.util.List;

public class QuilometragemService {

    public static void normalizarQuilometragem(List<OfertaComPontuacao> ofertas) {
        if( ofertas == null || ofertas.isEmpty() ){
            throw  new IllegalArgumentException("A Lista de Ofertas não pode ser vazia");
        }

        double menorQuilometragem = 0;
        double maiorQuilometragem = 0;

        // Encontrar a menor e a maior quilometragem
        for (OfertaComPontuacao oferta : ofertas) {
            if (oferta.getQuilometragem() < menorQuilometragem) {
                menorQuilometragem = oferta.getQuilometragem();
            }
            if (oferta.getQuilometragem() > maiorQuilometragem) {
                maiorQuilometragem = oferta.getQuilometragem();
            }
        }

        // Normalizar quilometragem
        double range = maiorQuilometragem - menorQuilometragem;
        if (range >= 0) {
            for (OfertaComPontuacao oferta : ofertas) {
                double scoreNormalizado = 10 * (maiorQuilometragem - oferta.getQuilometragem()) / range;
                oferta.getOfertaNormalizada().setQuilometragem(scoreNormalizado);
            }
        }
    }


}
