package br.gov.forum.forumrelatos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name = "cadastro_relato")
@Data
public class CadastroRelato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cadastro_relato")
    private Long id;

    /** (nullable = false) para nao ter valor nulos e obrigatorios no banco e usei unique para garantir que algumas
     * coluna tenha um  valores unicos
     * */
    @Column(name = "tipo_relato", nullable = false, columnDefinition = "TEXT")
    private String tipoRelato;

    @Column(name = "data_ocorrida", nullable = false)
    private LocalDate dataOcorrida;

    @Column(name = "horario", nullable = false)
    private LocalTime horario;

    @Column(name = "sistema_relacionado", nullable = false, columnDefinition = "TEXT")
    private String sistemaRelacionado;

    @Column(name = "consentimento_responsabilidade", nullable = false)
    private Boolean consentimentoResponsabilidade;

    @Column(name = "aceite_termos", nullable = false)
    private Boolean aceiteTermos;

    @Column(name = "codigo_relato", length = 10, unique = true, updatable = false, insertable = false)
    private String codigoRelato;

    @Column(name = "status_relato", length = 30)
    private String statusRelato = "Em análise";

    @Column(name = "foi_vitima", nullable = false)
    private Boolean foiVitima;

    @Column(name = "quantidade_outras_pessoas")
    private Integer quantidadeOutrasPessoas;

    @Column(name = "outras_pessoas_vitimas", nullable = false)
    private Boolean outrasPessoasVitimas;

    @Column(name = "tomou_ciencia", nullable = false, length = 50)
    private String tomouCiencia = "não informado"; // assim como coloquei no banco deixei um valor preenchido por segurança

    @Column(name = "descricao_ocorrido", nullable = false, columnDefinition = "TEXT")
    private String descricaoOcorrido;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    /**Relacionamento com a tabela usuario*/

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    /**Metodo para passar a data e a hora altomaticamnte caso o json nao envie*/
    @PrePersist
    public void prePersist() {
        if (dataRegistro == null) {
            dataRegistro = LocalDateTime.now();
        }
    }

}
