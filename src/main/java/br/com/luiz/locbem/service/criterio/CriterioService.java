package br.com.luiz.locbem.service.criterio;

import br.com.luiz.locbem.model.offer.CaracteristicaVeiculo;
import br.com.luiz.locbem.model.offer.OfertaComPontuacao;
import br.com.luiz.locbem.model.user.PreferenciaUsuario;

import java.util.List;

public class CriterioService {

    public static void normalizarTodosCriterios(PreferenciaUsuario preferenciaUsuario, List<OfertaComPontuacao> ofertas) {
        normalizarPreco(ofertas);
        normalizarQuilometragem(ofertas);
        normalizarTipoVeiculo(preferenciaUsuario, ofertas);
        normalizarTipoCombustivel(preferenciaUsuario, ofertas);
        normalizarEstadoVeiculo(preferenciaUsuario, ofertas);
        normalizarCaracteristicas(preferenciaUsuario, ofertas);
    }


    private static void normalizarPreco(List<OfertaComPontuacao> ofertas) {
        double menorPreco = 0;
        double maiorpreco = 0;

        // Encontrar a menor e a maior quilometragem
        for (OfertaComPontuacao oferta : ofertas) {
            if (oferta.getPreco() < menorPreco) {
                menorPreco = oferta.getPreco();
            }
            if (oferta.getPreco() > maiorpreco) {
                maiorpreco = oferta.getPreco();
            }
        }

        // Normalizar Preco
        double range = maiorpreco - menorPreco;
        if (range >= 0) {
            for (OfertaComPontuacao oferta : ofertas) {
                double scoreNormalizado = 10 * (maiorpreco - oferta.getQuilometragem()) / range;
                oferta.getOfertaNormalizada().setPreco(scoreNormalizado);
            }
        }
    }
    private static void normalizarQuilometragem(List<OfertaComPontuacao> ofertas) {

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

    private static void normalizarTipoVeiculo(PreferenciaUsuario preferenciaUsuario, List<OfertaComPontuacao> ofertas) {
        ofertas.forEach(oferta -> {
            if(preferenciaUsuario.getTipoVeiculo().contains(oferta.getTipoVeiculo())){
                oferta.getOfertaNormalizada().setTipoVeiculo(10);
            }else {
                oferta.getOfertaNormalizada().setTipoVeiculo(0);
            }
        });
    }

    private static void normalizarTipoCombustivel(PreferenciaUsuario preferenciaUsuario, List<OfertaComPontuacao> ofertas) {
        ofertas.forEach(oferta -> {
            if(preferenciaUsuario.getCombustivel().contains(oferta.getCombustivel())){
                oferta.getOfertaNormalizada().setCombustivel(10);
            }else {
                oferta.getOfertaNormalizada().setCombustivel(0);
            }
        });
    }

    private static void normalizarEstadoVeiculo(PreferenciaUsuario preferenciaUsuario, List<OfertaComPontuacao> ofertas) {
        ofertas.forEach(oferta -> {
            if(preferenciaUsuario.getEstadoVeiculo().contains(oferta.getEstadoVeiculo())){
                oferta.getOfertaNormalizada().setEstadoVeiculo(10);
            }else {
                oferta.getOfertaNormalizada().setEstadoVeiculo(0);
            }
        });
    }

    private static void normalizarCaracteristicas(PreferenciaUsuario preferenciaUsuario, List<OfertaComPontuacao> ofertas) {

        int caracteristicasAtendidas = 0;
        int   pontuacaoMaxima = 10;

        for( OfertaComPontuacao oferta : ofertas){
            caracteristicasAtendidas = 0;
            for (CaracteristicaVeiculo caracteristica : oferta.getCaracteristica()) {
                if (preferenciaUsuario.getCaracteristicas().contains(caracteristica)) {
                    caracteristicasAtendidas++;
                }
            }
            oferta.getOfertaNormalizada().setCaracteristicas(((double) caracteristicasAtendidas / preferenciaUsuario.getCaracteristicas().size()) * pontuacaoMaxima);
        }
    }

    private static double encontrarMaiorPreco(List<OfertaComPontuacao> ofertas) {
        double maiorPreco = 0;
        for (OfertaComPontuacao oferta : ofertas) {
            if (oferta.getPreco() > maiorPreco) {
                maiorPreco = oferta.getPreco();
            }
        }
        return maiorPreco;
    }

}
