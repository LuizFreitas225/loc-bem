package br.com.luiz.locbem.service.criterio;

import br.com.luiz.locbem.model.offer.OfertaComPontuacao;

import java.util.List;

public class PrecoService {

    public static void normalizarPrecos(List<OfertaComPontuacao> ofertas) {
        double maiorPreco = encontrarMaiorPreco(ofertas);
        if (maiorPreco > 0) {
            for (OfertaComPontuacao oferta : ofertas) {
                double precoNormalizado = (oferta.getPreco() / maiorPreco) * 10;
                oferta.getOfertaNormalizada().setPreco(precoNormalizado);
            }
        }
    }

    private static double encontrarMaiorPreco(List<OfertaComPontuacao> ofertas) {
        if( ofertas == null || ofertas.isEmpty() ){
             throw  new IllegalArgumentException("A Lista de Ofertas não pode ser vazia");
        }
        double maiorPreco = 0;
        for (OfertaComPontuacao oferta : ofertas) {
            if (oferta.getPreco() > maiorPreco) {
                maiorPreco = oferta.getPreco();
            }
        }
        return maiorPreco;
    }

}
