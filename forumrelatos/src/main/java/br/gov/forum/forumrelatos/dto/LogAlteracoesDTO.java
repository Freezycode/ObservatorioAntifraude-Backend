package br.gov.forum.forumrelatos.dto;

import lombok.Data;

@Data
public class LogAlteracoesDTO {
    private String tabelaAfetada;
    private Long registroId;
    private String acao;
    //private String dadosAnteriores;
    private String dadosNovos;
    private Long usuarioId;
}
