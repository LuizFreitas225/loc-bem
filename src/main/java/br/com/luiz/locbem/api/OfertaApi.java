package br.com.luiz.locbem.api;

import br.com.luiz.locbem.dto.oferta.CreateOfertaDTO;
import br.com.luiz.locbem.dto.oferta.UpdateOfertaDTO;
import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oferta")
public class OfertaApi {
    private final OfertaService ofertaService;
    private final ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<Oferta> criarOferta(@RequestBody CreateOfertaDTO dto) {
        Oferta novaOferta = ofertaService.criarOferta(modelMapper.map(dto, Oferta.class));
        return new ResponseEntity<>(novaOferta, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Oferta> atualizarOferta( @RequestBody UpdateOfertaDTO dto) {
        Oferta ofertaAtualizada = ofertaService.atualizarOferta(modelMapper.map(dto, Oferta.class));
        return new ResponseEntity<>(ofertaAtualizada, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oferta> buscarOfertaPorId(@PathVariable Long id) {
        Oferta oferta = ofertaService.buscarOfertaPorId(id);
        return new ResponseEntity<>(oferta, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Oferta>> listarOfertas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String searchTerm) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Oferta> ofertas = ofertaService.listarOfertas(pageRequest, searchTerm);
        return new ResponseEntity<>(ofertas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOferta(@PathVariable Long id) {
        ofertaService.deletarOferta(id);
        return ResponseEntity.noContent().build();
    }
}
