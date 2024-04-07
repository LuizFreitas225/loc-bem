package br.com.luiz.locbem.service;

import br.com.luiz.locbem.exception.OfertaNotFoundException;
import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.repository.OfertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfertaService {
    private final OfertaRepository ofertaRepository;

    public Oferta criarOferta(Oferta oferta) {
        return ofertaRepository.save(oferta);
    }

    public Oferta atualizarOferta(Oferta oferta) {
        if (!ofertaRepository.existsById(oferta.getId())) {
            throw new OfertaNotFoundException();
        }
        return ofertaRepository.save(oferta);
    }

    public Page<Oferta> listarOfertas(PageRequest pageRequest, String searchTerm) {
        return ofertaRepository.findAByModeloAndDescricao(pageRequest,searchTerm);
    }

    public Oferta buscarOfertaPorId(Long id) {
        return ofertaRepository.findById(id)
                .orElseThrow(OfertaNotFoundException::new);
    }

    public void deletarOferta(Long id) {
        if (!ofertaRepository.existsById(id)) {
            throw new OfertaNotFoundException();
        }
        ofertaRepository.deleteById(id);
    }
}
