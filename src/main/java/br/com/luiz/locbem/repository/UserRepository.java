package br.com.luiz.locbem.repository;

import br.com.luiz.locbem.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByPersonRegistration(String personRegistration);

    User findByEmail(String email);

    @Query(value = " SELECT p FROM User p "
            + " WHERE LOWER (p.name ) LIKE %:searchTerm% "
            + " OR LOWER (p.email) LIKE %:searchTerm%")
    Page<User> findAByNameOrEmailOrUserName(PageRequest pageRequest, String searchTerm);
}
