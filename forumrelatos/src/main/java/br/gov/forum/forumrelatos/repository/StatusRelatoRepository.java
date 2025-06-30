package br.gov.forum.forumrelatos.repository;

import br.gov.forum.forumrelatos.model.CadastroRelato;
import br.gov.forum.forumrelatos.model.StatusRelato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRelatoRepository extends JpaRepository<StatusRelato, Long> {
    List<StatusRelato> findByCadastroRelato(CadastroRelato cadastroRelato);
}
