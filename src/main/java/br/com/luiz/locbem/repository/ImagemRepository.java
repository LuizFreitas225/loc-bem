package br.com.luiz.locbem.repository;

import br.com.luiz.locbem.model.offer.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
}
