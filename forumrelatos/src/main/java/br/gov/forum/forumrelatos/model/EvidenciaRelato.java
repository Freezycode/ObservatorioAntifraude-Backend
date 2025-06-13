package br.gov.forum.forumrelatos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "evidencia_relato")
@Data
public class EvidenciaRelato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evidencia")
    private Long id;
    @Column(name = "tipo_arquivo", length = 50)
    private String tipoArquivo;

    @Column(name = "caminho_arquivo", columnDefinition = "TEXT")
    private String caminhoArquivo;

    @Column(name = "data_envio", nullable = false)
    private LocalDateTime dataEnvio;

    @ManyToOne
    @JoinColumn(name = "id_relato", nullable = false)
    private CadastroRelato cadastroRelato;
}
