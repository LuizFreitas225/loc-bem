package br.com.luiz.locbem.util;

import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.offer.OfertaComPontuacao;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


public class ModelMapperUtil {

    public static List<OfertaComPontuacao> toOfertaComPontuacao(List<Oferta> ofertas, ModelMapper modelMapper){
        List<OfertaComPontuacao> ofertaComPontuacaoList = new ArrayList<>();

        ofertas.forEach(oferta -> {
            ofertaComPontuacaoList.add(modelMapper.map(ofertas, OfertaComPontuacao.class));
        });
        return  ofertaComPontuacaoList;
    }
}
