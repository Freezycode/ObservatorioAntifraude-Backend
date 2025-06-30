package br.gov.forum.forumrelatos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario", schema = "public")
@Getter
@Setter
@ToString(exclude = "cadastroRelatos") 
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean anonimo;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "CPF", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(length = 150)
    private String email;

    @Column(length = 50)
    private String telefone;

    @Column(name = "CEP", length = 9)
    private String cep;

    @Column(length = 100)
    private String cidade;

    @Column(length = 100)
    private String estado;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    @Column
    private Boolean ativo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<CadastroRelato> cadastroRelatos;

    @PrePersist
    public void prePersist() {
        if (dataRegistro == null) {
            dataRegistro = LocalDateTime.now();
        }
    }
}

