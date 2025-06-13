package br.gov.forum.forumrelatos.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CadastroRelatoStatusDTO {

    private Long usuarioId;
    private String codigoRelato;
    private String tipoRelato;
    private String sistemaRelacionado;
    private LocalDateTime dataRegistro;

}
