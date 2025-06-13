package br.gov.forum.forumrelatos.model;

import jakarta.persistence.*;
import lombok.Data; // aqui já ultilizei o data que alem de gera os  getters e setters, hashCode, tostring e o construtor

import java.time.LocalDateTime;

@Entity
@Table(name = "status_relato")
@Data
public class StatusRelato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status_relato")
    private Long id;

    @Column(name = "status", length = 50)
    private String statusRelato = "Relato encaminhado";

    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;

    /**
     * relacionamento das tabelas status e relatos de muitos para um
     */
    @ManyToOne
    @JoinColumn(name = "id_cadastro_relato", nullable = false)
    private CadastroRelato cadastroRelato;

    /**
     * metodo para garantir que a data de registro seja preenchida automaticamente caso não seja enviada
     */
    @PrePersist  /**metodo especial que o jpa chama automaticamente antes de salvar no banco*/
    public void prePersist() {
        if (dataRegistro == null) {
            dataRegistro = LocalDateTime.now();
        }
    }
}
