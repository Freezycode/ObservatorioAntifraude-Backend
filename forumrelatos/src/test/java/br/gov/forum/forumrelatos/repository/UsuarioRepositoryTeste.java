package br.gov.forum.forumrelatos.repository;

import br.gov.forum.forumrelatos.model.Usuario;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuarioRepositoryTeste {

        @Autowired
        UsuarioRepository repository;

        @Test
        public void salvarTest(){

                Usuario usuario = new Usuario();
                usuario.setAnonimo(false);
                usuario.setNome("João Lucas Cardoso");
                usuario.setCpf("24665678907");
                usuario.setEmail("Joao@email.com");
                usuario.setTelefone("61999988877");
                usuario.setCep("12345-999");
                usuario.setCidade("Brasilia");
                usuario.setEstado("DF");
                usuario.setAtivo(true);

                var usuarioSalvo = repository.save(usuario);
                System.out.println("Usuario Salvo: " + usuarioSalvo);
        }


}
