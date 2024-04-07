package br.com.luiz.locbem.repository;

import br.com.luiz.locbem.model.offer.Oferta;
import br.com.luiz.locbem.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    @Query(value = " SELECT p FROM Oferta p "
            + " WHERE LOWER (p.modelo ) LIKE %:searchTerm% "
            + " OR LOWER (p.descricao) LIKE %:searchTerm%")
    Page<Oferta> findAByModeloAndDescricao(PageRequest pageRequest, String searchTerm);
}
