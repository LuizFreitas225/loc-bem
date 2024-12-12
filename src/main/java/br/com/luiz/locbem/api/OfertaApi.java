package br.com.luiz.locbem.api;

import br.com.luiz.locbem.dto.georeferencing.CoordinatesDTO;
import br.com.luiz.locbem.dto.oferta.CreateOfertaDTO;
import br.com.luiz.locbem.dto.oferta.OfertaDTO;
import br.com.luiz.locbem.dto.oferta.UpdateOfertaDTO;
import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.offer.OfertaComPontuacao;
import br.com.luiz.locbem.model.offer.OfertaSemPontuacao;
import br.com.luiz.locbem.model.user.PreferenciaUsuario;
import br.com.luiz.locbem.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oferta")
public class OfertaApi {
    private final OfertaService ofertaService;
    private final ModelMapper modelMapper;



    @PostMapping
    public ResponseEntity<OfertaDTO> criarOferta(@RequestBody @Valid  CreateOfertaDTO dto) {
        Oferta novaOferta = ofertaService.criarOferta(modelMapper.map(dto, Oferta.class));
        return new ResponseEntity<>(modelMapper.map(novaOferta, OfertaDTO.class), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<OfertaDTO> atualizarOferta( @RequestBody @Valid UpdateOfertaDTO dto) {
        Oferta ofertaAtualizada = ofertaService.atualizarOferta(modelMapper.map(dto, Oferta.class));
        return new ResponseEntity<>(modelMapper.map(ofertaAtualizada, OfertaDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<OfertaSemPontuacao>> listarOfertas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false, defaultValue = "") String searchTerm,
            @RequestParam(required = true, defaultValue = "") double latitude,
            @RequestParam(required = true, defaultValue = "") double longitude) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OfertaSemPontuacao> ofertas = ofertaService.listarOfertasSemPontuacao(pageRequest,  new CoordinatesDTO(latitude, longitude), searchTerm);
        return new ResponseEntity<>(ofertas, HttpStatus.OK);
    }

    @PostMapping("/list-as-post")
    public ResponseEntity<Page<OfertaComPontuacao>> listarOfertasAsPost(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false, defaultValue = "") String searchTerm,
            @RequestBody() @Valid PreferenciaUsuario preferenciaUsuario) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OfertaComPontuacao> ofertas = ofertaService.listarOfertasComPreferencia(pageRequest, searchTerm, preferenciaUsuario);
        return new ResponseEntity<>(ofertas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOferta(@PathVariable Long id) {
        ofertaService.deletarOferta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertaSemPontuacao> buscarPorId(@PathVariable Long id,
                                                          @RequestParam(required = true, defaultValue = "") double latitude,
                                                          @RequestParam(required = true, defaultValue = "") double longitude) {
        OfertaSemPontuacao oferta =  ofertaService.buscarOfertaPorId(id, new CoordinatesDTO(latitude, longitude));
        return ResponseEntity.ok(oferta);
    }

    @PostMapping("/{id}/imagens")
    public ResponseEntity<String> adicionarImagens(@PathVariable Long id, @RequestParam("imagens") List<MultipartFile> imagens) {
        try {
            ofertaService.adicionarImagens(id, imagens);
            return ResponseEntity.ok("Imagens adicionadas com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar imagens: " + e.getMessage());
        }
    }
}
