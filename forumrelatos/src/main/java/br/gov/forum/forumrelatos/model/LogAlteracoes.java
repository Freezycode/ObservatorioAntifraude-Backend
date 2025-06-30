package br.gov.forum.forumrelatos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "log_alteracoes")
@Data
public class LogAlteracoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tabela_afetada", nullable = false, length = 150)
    private String tabelaAfetada;

    @Column(name = "registro_id", nullable = false)
    private Long registroId;

    @Column(name = "acao", nullable = false, length = 10)
    private String acao;

    @Column(name = "dados_anteriores", columnDefinition = "text")
    private String dadosAnteriores;

    @Column(name = "dados_novos", columnDefinition = "text")
    private String dadosNovos;

    @Column(name = "data_acao", nullable = false)
    private LocalDateTime dataAcao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
