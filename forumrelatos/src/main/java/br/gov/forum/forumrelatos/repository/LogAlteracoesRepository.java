package br.gov.forum.forumrelatos.repository;

import br.gov.forum.forumrelatos.model.LogAlteracoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogAlteracoesRepository extends JpaRepository<LogAlteracoes, Long> {
}
