package br.gov.forum.forumrelatos.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DetalhesRelatoCompletoDTO {
    private String codigoRelato;
    private String tipoRelato;
    private String sistemaRelacionado;
    private LocalDateTime dataRegistro;

    private List<StatusRelatoHistoricoDTO> historico; 
}
