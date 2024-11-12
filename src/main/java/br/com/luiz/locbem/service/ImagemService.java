package br.com.luiz.locbem.service;

import br.com.luiz.locbem.dto.georeferencing.CoordinatesDTO;
import br.com.luiz.locbem.exception.ForbiddenException;
import br.com.luiz.locbem.exception.ImageNotFoundException;
import br.com.luiz.locbem.exception.OfertaNotFoundException;
import br.com.luiz.locbem.exception.ProcessImageException;
import br.com.luiz.locbem.model.offer.Imagem;
import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.offer.OfertaComPontuacao;
import br.com.luiz.locbem.model.user.PreferenciaUsuario;
import br.com.luiz.locbem.model.user.User;
import br.com.luiz.locbem.repository.ImagemRepository;
import br.com.luiz.locbem.repository.OfertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ImagemService {

    private final ImagemRepository imagemRepository;

    public Imagem getImagensById(long id) {
        return imagemRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }

}
