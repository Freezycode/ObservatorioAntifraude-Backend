package br.gov.forum.forumrelatos.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatusRelatoHistoricoDTO {

    private String statusRelato;
    private LocalDateTime dataRegistro;
}
