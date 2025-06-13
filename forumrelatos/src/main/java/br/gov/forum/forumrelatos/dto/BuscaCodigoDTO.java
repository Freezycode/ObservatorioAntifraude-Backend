package br.gov.forum.forumrelatos.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BuscaCodigoDTO {

    private String codigoRelato;
    private String tipoRelato;
    private String sistemaRelacionado;
    private String statusRelato;
    private LocalDateTime dataRegistro;
}

