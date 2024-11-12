package br.com.luiz.locbem.api;

import br.com.luiz.locbem.model.offer.Imagem;
import br.com.luiz.locbem.service.ImagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
