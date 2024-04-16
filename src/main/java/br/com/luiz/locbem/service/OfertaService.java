package br.com.luiz.locbem.service;

import br.com.luiz.locbem.exception.ForbiddenException;
import br.com.luiz.locbem.exception.OfertaNotFoundException;
import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.user.User;
import br.com.luiz.locbem.repository.OfertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OfertaService {
    private final OfertaRepository ofertaRepository;
    private final UserService userService;

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

    public Page<Oferta> listarOfertas(PageRequest pageRequest, String searchTerm) {
        return ofertaRepository.findAByModeloAndDescricao(pageRequest,searchTerm);
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

}
