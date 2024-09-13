package br.com.luiz.locbem.service;

import br.com.luiz.locbem.dto.georeferencing.CoordinatesDTO;
import br.com.luiz.locbem.exception.ForbiddenException;
import br.com.luiz.locbem.exception.OfertaNotFoundException;
import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.offer.OfertaComPontuacao;
import br.com.luiz.locbem.model.user.PreferenciaUsuario;
import br.com.luiz.locbem.model.user.User;
import br.com.luiz.locbem.repository.OfertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfertaService {
    private final OfertaRepository ofertaRepository;
    private final UserService userService;
    private final AHPService ahpService;

    @Transactional
    public Oferta criarOferta(Oferta oferta) {
        User user = validCurrentUser();
        oferta.setUser(user);
        return ofertaRepository.save(oferta);
    }

    @Transactional
    public Oferta atualizarOferta(Oferta novaOferta) {
        Oferta ofertaAtual = ofertaRepository.findById(novaOferta.getId()).orElseThrow(OfertaNotFoundException::new);

        if( ofertaAtual.getUser().equals(validCurrentUser())){
            novaOferta.setUser(ofertaAtual.getUser());
            return ofertaRepository.save(novaOferta);
        }
        throw new ForbiddenException();
    }

    public Page<OfertaComPontuacao> listarOfertas(PageRequest pageRequest, String searchTerm,
                                                  PreferenciaUsuario preferenciaUsuario) {

        Page<Oferta> ofertas = this.findAByModeloAndDescricaoAndFilterForDistance(pageRequest,
                searchTerm, preferenciaUsuario.getDistanciaMaxima(), preferenciaUsuario.getCoordenadasUsuario());

        List<OfertaComPontuacao> ofertasComPontuacaoList = ahpService.calcularPontuacao(preferenciaUsuario, ofertas.getContent());

        ofertasComPontuacaoList.sort(Comparator.comparing(OfertaComPontuacao::getPontuacao).reversed());



        Page<OfertaComPontuacao> ofertasComPontuacao = new PageImpl<>(ofertasComPontuacaoList, pageRequest, ofertas.getTotalElements());

        return ofertasComPontuacao;
    }

    public Oferta buscarOfertaPorId(Long id) {
        return ofertaRepository.findById(id)
                .orElseThrow(OfertaNotFoundException::new);
    }

    public void deletarOferta(Long id) {
        Oferta oferta = ofertaRepository.findById(id).orElseThrow(OfertaNotFoundException::new);
        if( oferta.getUser().equals(validCurrentUser())){
             ofertaRepository.deleteById(id);
        }   else throw new ForbiddenException();
    }

    private  User validCurrentUser(){
        try{
            return userService.getUserAuthenticated();
        }
        catch (Exception e){
            throw new ForbiddenException();
        }
    }

    private Page<Oferta> findAByModeloAndDescricaoAndFilterForDistance(PageRequest pageRequest, String searchTerm, int maxDistance,
                                                                      CoordinatesDTO userCoordinates) {

        Page<Oferta> ofertas = ofertaRepository.findAByModeloAndDescricao(pageRequest, searchTerm);
        List<Oferta> ofertasFiltradas = applyDistanceFilter(ofertas.getContent(), maxDistance, userCoordinates);

        while (ofertasFiltradas.size() < pageRequest.getPageSize() && ofertas.hasNext()) {
            pageRequest = (PageRequest) ofertas.nextPageable();
            ofertas = ofertaRepository.findAByModeloAndDescricao(pageRequest, searchTerm);
            ofertasFiltradas.addAll(applyDistanceFilter(ofertas.getContent(), maxDistance, userCoordinates));
        }

        return new PageImpl<>(ofertasFiltradas, pageRequest, ofertasFiltradas.size());
    }

    private List<Oferta> applyDistanceFilter(List<Oferta> ofertas, int maxDistance,
                                             CoordinatesDTO userCoordinates) {
        return ofertas.stream()
                .filter(oferta -> MapboxDistanceService.getDistanceInKilometer(userCoordinates,
                        new CoordinatesDTO(oferta.getLatitude(), oferta.getLongitude())) <= maxDistance)
                .collect(Collectors.toList());
    }

}
