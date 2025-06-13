package br.gov.forum.forumrelatos.controller;

import br.gov.forum.forumrelatos.model.Usuario;
import br.gov.forum.forumrelatos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setCpf(usuarioAtualizado.getCpf());
            usuario.setTelefone(usuarioAtualizado.getTelefone());
            usuario.setCep(usuarioAtualizado.getCep());
            usuario.setCidade(usuarioAtualizado.getCidade());
            usuario.setEstado(usuarioAtualizado.getEstado());
            usuario.setAnonimo(usuarioAtualizado.getAnonimo());
            usuario.setAtivo(usuarioAtualizado.getAtivo());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario não encontrado ou nao existe" + id));
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario não deletado ou naão encontrado" + id);
        }
        usuarioRepository.deleteById(id);
    }
}

