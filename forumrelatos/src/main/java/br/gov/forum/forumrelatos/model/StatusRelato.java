package br.gov.forum.forumrelatos.model;

import jakarta.persistence.*;
import lombok.Data;
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

    @ManyToOne
    @JoinColumn(name = "id_cadastro_relato", nullable = false)
    private CadastroRelato cadastroRelato;

    @PrePersist  
    public void prePersist() {
        if (dataRegistro == null) {
            dataRegistro = LocalDateTime.now();
        }
    }
}
