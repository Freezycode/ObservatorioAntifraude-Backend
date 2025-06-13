package br.gov.forum.forumrelatos.repository;

import br.gov.forum.forumrelatos.model.CadastroRelato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CadastroRelatoRepository extends JpaRepository<CadastroRelato, Long> {

    Optional<CadastroRelato> findByCodigoRelato(String codigoRelato);
}

