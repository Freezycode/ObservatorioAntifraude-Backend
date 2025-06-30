package br.gov.forum.forumrelatos.repository;

import br.gov.forum.forumrelatos.model.CadastroRelato;
import br.gov.forum.forumrelatos.model.StatusRelato;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class StatusRelatoRepositoryTest {

    @Autowired
    StatusRelatoRepository statusRelatoRepository;

    @Autowired
    CadastroRelatoRepository cadastroRelatoRepository;

    @Test
    void salvarTest() {

        CadastroRelato cadastroRelato = cadastroRelatoRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Cadastro de Relato não encontrado!"));

        StatusRelato statusRelato = new StatusRelato();
        statusRelato.setCadastroRelato(cadastroRelato);
        statusRelato.setStatusRelato("Em andamento");
        statusRelato.setDataRegistro(LocalDateTime.now());

        StatusRelato salvo = statusRelatoRepository.save(statusRelato);
        System.out.println("Status Relato salvo: " + salvo);
    }

}
