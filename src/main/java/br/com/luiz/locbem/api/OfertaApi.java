package br.com.luiz.locbem.api;

import br.com.luiz.locbem.dto.oferta.CreateOfertaDTO;
import br.com.luiz.locbem.dto.oferta.OfertaDTO;
import br.com.luiz.locbem.dto.oferta.UpdateOfertaDTO;
import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.offer.OfertaComPontuacao;
import br.com.luiz.locbem.model.user.PreferenciaUsuario;
import br.com.luiz.locbem.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<OfertaComPontuacao>> listarOfertas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false, defaultValue = "") String searchTerm,
            @RequestBody(required = false) PreferenciaUsuario preferenciaUsuario) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OfertaComPontuacao> ofertas = ofertaService.listarOfertas(pageRequest, searchTerm, preferenciaUsuario);
        return new ResponseEntity<>(ofertas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOferta(@PathVariable Long id) {
        ofertaService.deletarOferta(id);
        return ResponseEntity.noContent().build();
    }
}
