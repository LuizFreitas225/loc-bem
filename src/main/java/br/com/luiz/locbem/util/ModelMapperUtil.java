package br.com.luiz.locbem.util;

import br.com.luiz.locbem.model.offer.Imagem;
import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.offer.OfertaComPontuacao;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ModelMapperUtil {

    public static List<OfertaComPontuacao> toOfertaComPontuacao(List<Oferta> ofertas, ModelMapper modelMapper){
        List<OfertaComPontuacao> ofertaComPontuacaoList = new ArrayList<>();

        ofertas.forEach(oferta -> {
            OfertaComPontuacao ofertaComPontuacao = modelMapper.map(oferta, OfertaComPontuacao.class);
            ofertaComPontuacao.setLinkImagens(oferta.getImagens().stream().map(Imagem::getLinkImage).collect(Collectors.toList()));
            ofertaComPontuacaoList.add(ofertaComPontuacao);
        });
        return  ofertaComPontuacaoList;
    }
}

