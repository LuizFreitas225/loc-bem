package br.com.luiz.locbem.api;

import br.com.luiz.locbem.dto.oferta.CreateOfertaDTO;
import br.com.luiz.locbem.dto.oferta.OfertaDTO;
import br.com.luiz.locbem.dto.oferta.UpdateOfertaDTO;
import br.com.luiz.locbem.model.offer.Imagem;
import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.offer.OfertaComPontuacao;
import br.com.luiz.locbem.model.user.PreferenciaUsuario;
import br.com.luiz.locbem.service.ImagemService;
import br.com.luiz.locbem.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/imagem")
public class ImagemApi {
    private final ImagemService imagemService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImagem(@PathVariable Long id) {
        Imagem imagem = imagemService.getImagensById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imagem.getByteImage());
        }

}
