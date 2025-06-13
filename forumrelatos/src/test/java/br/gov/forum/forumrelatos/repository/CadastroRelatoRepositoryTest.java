package br.gov.forum.forumrelatos.repository;

import br.gov.forum.forumrelatos.model.CadastroRelato;
import br.gov.forum.forumrelatos.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
class CadastroRelatoRepositoryTest {

    @Autowired
    CadastroRelatoRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    void salvarTest() {
        Usuario usuario = usuarioRepository.findByCpf("24665678907")
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        CadastroRelato cadastroRelato = new CadastroRelato();
        cadastroRelato.setUsuario(usuario);
        cadastroRelato.setTipoRelato("Roubo de conta");
        cadastroRelato.setDataOcorrida(LocalDate.now());
        cadastroRelato.setHorario(LocalTime.now());
        cadastroRelato.setSistemaRelacionado("RADAR");
        cadastroRelato.setConsentimentoResponsabilidade(true);
        cadastroRelato.setAceiteTermos(true);
        cadastroRelato.setStatusRelato("Em análise");
        cadastroRelato.setDataRegistro(LocalDateTime.now());
        cadastroRelato.setFoiVitima(true);
        cadastroRelato.setOutrasPessoasVitimas(false);
        cadastroRelato.setTomouCiencia("SMS");
        cadastroRelato.setDescricaoOcorrido("Minha conta foi roubada, não tenho mais acesso.");

        CadastroRelato salvo = repository.save(cadastroRelato);
        System.out.println("Relato salvo: " + salvo);
    }
}
