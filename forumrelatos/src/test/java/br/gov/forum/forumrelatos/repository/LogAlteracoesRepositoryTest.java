package br.gov.forum.forumrelatos.repository;

import br.gov.forum.forumrelatos.model.LogAlteracoes;
import br.gov.forum.forumrelatos.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class LogAlteracoesRepositoryTest {

    @Autowired
    LogAlteracoesRepository logAlteracoesRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void salvarTest() {

        Usuario usuario = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        LogAlteracoes log = new LogAlteracoes();
        log.setTabelaAfetada("cadastro_relato");
        log.setRegistroId(2L); 
        log.setAcao("UPDATE");
        log.setDadosAnteriores("{\"status_relato\": \"Em análise\"}");
        log.setDadosNovos("{\"status_relato\": \"Aprovado\"}");
        log.setUsuario(usuario);
        log.setDataAcao(LocalDateTime.now());

        LogAlteracoes salvo = logAlteracoesRepository.save(log);
        System.out.println("Log de Alterações salvo: " + salvo);
    }
}
