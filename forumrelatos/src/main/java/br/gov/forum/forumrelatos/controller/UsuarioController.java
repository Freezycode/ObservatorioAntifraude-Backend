package br.gov.forum.forumrelatos.controller;

import br.gov.forum.forumrelatos.model.LogAlteracoes;
import br.gov.forum.forumrelatos.model.Usuario;
import br.gov.forum.forumrelatos.repository.LogAlteracoesRepository;
import br.gov.forum.forumrelatos.repository.UsuarioRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LogAlteracoesRepository logAlteracoesRepository;

    private String toJson(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            return "Erro ao gerar JSON: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioRepository.save(usuario);

        LogAlteracoes log = new LogAlteracoes();
        log.setTabelaAfetada("usuario");
        log.setRegistroId(novoUsuario.getId());
        log.setAcao("INSERT");
        log.setDadosAnteriores(null);
        log.setDadosNovos(toJson(novoUsuario));
        log.setUsuario(novoUsuario);
        log.setDataAcao(LocalDateTime.now());

        logAlteracoesRepository.save(log);

        return novoUsuario;
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            String dadosAntigos = toJson(usuario);

            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setCpf(usuarioAtualizado.getCpf());
            usuario.setTelefone(usuarioAtualizado.getTelefone());
            usuario.setCep(usuarioAtualizado.getCep());
            usuario.setCidade(usuarioAtualizado.getCidade());
            usuario.setEstado(usuarioAtualizado.getEstado());
            usuario.setAnonimo(usuarioAtualizado.getAnonimo());
            usuario.setAtivo(usuarioAtualizado.getAtivo());

            Usuario atualizado = usuarioRepository.save(usuario);

            LogAlteracoes log = new LogAlteracoes();
            log.setTabelaAfetada("usuario");
            log.setRegistroId(atualizado.getId());
            log.setAcao("UPDATE");
            log.setDadosAnteriores(dadosAntigos);
            log.setDadosNovos(toJson(atualizado));
            log.setUsuario(atualizado);
            log.setDataAcao(LocalDateTime.now());

            logAlteracoesRepository.save(log);

            return atualizado;
        }).orElseThrow(() -> new RuntimeException("Usuario não encontrado ou não existe: " + id));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioRepository.findById(id).ifPresent(usuario -> {
            usuarioRepository.deleteById(id);

            LogAlteracoes log = new LogAlteracoes();
            log.setTabelaAfetada("usuario");
            log.setRegistroId(usuario.getId());
            log.setAcao("DELETE");
            log.setDadosAnteriores(toJson(usuario));
            log.setDadosNovos(null);
            log.setUsuario(usuario);
            log.setDataAcao(LocalDateTime.now());

            logAlteracoesRepository.save(log);
        });
    }
}
