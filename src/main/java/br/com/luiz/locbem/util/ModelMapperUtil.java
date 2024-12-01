package br.com.luiz.locbem.util;

import br.com.luiz.locbem.dto.georeferencing.CoordinatesDTO;
import br.com.luiz.locbem.model.offer.Imagem;
import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.offer.OfertaComPontuacao;
import br.com.luiz.locbem.model.offer.OfertaSemPontuacao;
import br.com.luiz.locbem.service.MapboxDistanceService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ModelMapperUtil {

    public static List<OfertaComPontuacao> toOfertaComPontuacao(List<Oferta> ofertas, CoordinatesDTO userCordinates, ModelMapper modelMapper){
        List<OfertaComPontuacao> ofertaComPontuacaoList = new ArrayList<>();

        ofertas.forEach(oferta -> {
            OfertaComPontuacao ofertaComPontuacao = modelMapper.map(oferta, OfertaComPontuacao.class);
            ofertaComPontuacao.setLinkImagens(oferta.getImagens().stream().map(Imagem::getLinkImage).collect(Collectors.toList()));
            ofertaComPontuacao.setDistanciaEmKM(MapboxDistanceService.getDistanceInKilometer(userCordinates, new CoordinatesDTO(oferta.getLatitude(), oferta.getLongitude())));
            ofertaComPontuacaoList.add(ofertaComPontuacao);
        });
        return  ofertaComPontuacaoList;
    }

    public static List<OfertaSemPontuacao> toOfertasemPontuacao(List<Oferta> ofertas, CoordinatesDTO userCordinates, ModelMapper modelMapper){
        List<OfertaSemPontuacao> ofertaSemPontuacaoList = new ArrayList<>();

        ofertas.forEach(oferta -> {
            OfertaSemPontuacao ofertaSemPontuacao = modelMapper.map(oferta, OfertaSemPontuacao.class);
            ofertaSemPontuacao.setLinkImagens(oferta.getImagens().stream().map(Imagem::getLinkImage).collect(Collectors.toList()));
            ofertaSemPontuacao.setDistanciaEmKM(MapboxDistanceService.getDistanceInKilometer(userCordinates, new CoordinatesDTO(oferta.getLatitude(), oferta.getLongitude())));
            ofertaSemPontuacaoList.add(ofertaSemPontuacao);
        });
        return  ofertaSemPontuacaoList;
    }
}

