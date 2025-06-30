package br.gov.forum.forumrelatos.controller;

import br.gov.forum.forumrelatos.dto.CodigoRelatoDTO;
import br.gov.forum.forumrelatos.dto.DetalhesRelatoCompletoDTO;
import br.gov.forum.forumrelatos.dto.StatusRelatoHistoricoDTO;
import br.gov.forum.forumrelatos.model.CadastroRelato;
import br.gov.forum.forumrelatos.model.LogAlteracoes;
import br.gov.forum.forumrelatos.model.StatusRelato;
import br.gov.forum.forumrelatos.repository.CadastroRelatoRepository;
import br.gov.forum.forumrelatos.repository.LogAlteracoesRepository;
import br.gov.forum.forumrelatos.repository.StatusRelatoRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("cadastro-relatos")
public class RelatoController {

    @Autowired
    private CadastroRelatoRepository cadastroRelatoRepository;

    @Autowired
    private LogAlteracoesRepository logAlteracoesRepository;

    @Autowired
    private StatusRelatoRepository statusRelatoRepository;

    private String toJson(Object obj) {
        if (obj == null) return null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            return "Erro ao gerar JSON: " + e.getMessage();
        }
    }

    @GetMapping
    public List<CadastroRelato> listarTodos() {
        return cadastroRelatoRepository.findAll();
    }

    @GetMapping("/{id}")
    public CadastroRelato buscarPorId(@PathVariable Long id) {
        return cadastroRelatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro relato não encontrado: " + id));
    }

    @PostMapping
    public CadastroRelato salvar(@RequestBody CadastroRelato cadastroRelato) {
        CadastroRelato novoRelato = cadastroRelatoRepository.save(cadastroRelato);

        StatusRelato statusInicial = new StatusRelato();
        statusInicial.setCadastroRelato(novoRelato);
        statusInicial.setStatusRelato("Relato encaminhado");
        statusRelatoRepository.save(statusInicial);

        LogAlteracoes log = new LogAlteracoes();
        log.setTabelaAfetada("cadastro_relato");
        log.setRegistroId(novoRelato.getId());
        log.setAcao("INSERT");
        log.setDadosAnteriores(null);
        log.setDadosNovos(toJson(novoRelato));
        log.setUsuario(novoRelato.getUsuario());
        log.setDataAcao(LocalDateTime.now());
        logAlteracoesRepository.save(log);

        return novoRelato;
    }

    @PutMapping("/{id}")
    public CadastroRelato atualizar(@PathVariable Long id, @RequestBody CadastroRelato novoRelato) {
        CadastroRelato relatoExistente = cadastroRelatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Esse relato não existe ou não encontrado: " + id));

        String dadosAntigos = toJson(relatoExistente);

        relatoExistente.setTipoRelato(novoRelato.getTipoRelato());
        relatoExistente.setDataOcorrida(novoRelato.getDataOcorrida());
        relatoExistente.setHorario(novoRelato.getHorario());
        relatoExistente.setSistemaRelacionado(novoRelato.getSistemaRelacionado());
        relatoExistente.setConsentimentoResponsabilidade(novoRelato.getConsentimentoResponsabilidade());
        relatoExistente.setAceiteTermos(novoRelato.getAceiteTermos());
        relatoExistente.setFoiVitima(novoRelato.getFoiVitima());
        relatoExistente.setQuantidadeOutrasPessoas(novoRelato.getQuantidadeOutrasPessoas());
        relatoExistente.setOutrasPessoasVitimas(novoRelato.getOutrasPessoasVitimas());
        relatoExistente.setTomouCiencia(novoRelato.getTomouCiencia());
        relatoExistente.setDescricaoOcorrido(novoRelato.getDescricaoOcorrido());
        relatoExistente.setStatusRelato(novoRelato.getStatusRelato());

        CadastroRelato atualizado = cadastroRelatoRepository.save(relatoExistente);

        LogAlteracoes log = new LogAlteracoes();
        log.setTabelaAfetada("cadastro_relato");
        log.setRegistroId(atualizado.getId());
        log.setAcao("UPDATE");
        log.setDadosAnteriores(dadosAntigos);
        log.setDadosNovos(toJson(atualizado));
        log.setUsuario(atualizado.getUsuario());
        log.setDataAcao(LocalDateTime.now());
        logAlteracoesRepository.save(log);

        return atualizado;
    }

    @PutMapping("/{id}/aprovar")
    public CadastroRelato aprovarRelato(@PathVariable Long id) {
        CadastroRelato relato = cadastroRelatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relato não encontrado: " + id));

        String statusAntigo = relato.getStatusRelato();
        String statusNovo = "Aprovado";

        relato.setStatusRelato(statusNovo);
        CadastroRelato atualizado = cadastroRelatoRepository.save(relato);

        StatusRelato novoHistorico = new StatusRelato();
        novoHistorico.setCadastroRelato(atualizado);
        novoHistorico.setStatusRelato(statusNovo);
        statusRelatoRepository.save(novoHistorico);

        LogAlteracoes log = new LogAlteracoes();
        log.setTabelaAfetada("cadastro_relato");
        log.setRegistroId(atualizado.getId());
        log.setAcao("UPDATE");
        log.setDadosAnteriores("{ \"statusRelato\": \"" + statusAntigo + "\" }");
        log.setDadosNovos("{ \"statusRelato\": \"" + statusNovo + "\" }");
        log.setUsuario(atualizado.getUsuario());
        log.setDataAcao(LocalDateTime.now());
        logAlteracoesRepository.save(log);

        return atualizado;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        CadastroRelato relato = cadastroRelatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao deletar o relato: " + id));

        cadastroRelatoRepository.delete(relato);

        LogAlteracoes log = new LogAlteracoes();
        log.setTabelaAfetada("cadastro_relato");
        log.setRegistroId(relato.getId());
        log.setAcao("DELETE");
        log.setDadosAnteriores(toJson(relato));
        log.setDadosNovos(null);
        log.setUsuario(relato.getUsuario());
        log.setDataAcao(LocalDateTime.now());
        logAlteracoesRepository.save(log);
    }
  
    @GetMapping("/detalhes-completo")
    public DetalhesRelatoCompletoDTO buscarDetalhesComHistorico(@RequestParam String codigoRelato) {
        CadastroRelato relato = cadastroRelatoRepository.findByCodigoRelato(codigoRelato)
                .orElseThrow(() -> new RuntimeException("Relato não encontrado com código: " + codigoRelato));

        DetalhesRelatoCompletoDTO dto = new DetalhesRelatoCompletoDTO();
        dto.setCodigoRelato(relato.getCodigoRelato());
        dto.setTipoRelato(relato.getTipoRelato());
        dto.setSistemaRelacionado(relato.getSistemaRelacionado());
        dto.setDataRegistro(relato.getDataRegistro());

        List<StatusRelatoHistoricoDTO> historico = statusRelatoRepository.findByCadastroRelato(relato)
                .stream()
                .map(status -> {
                    StatusRelatoHistoricoDTO hDto = new StatusRelatoHistoricoDTO();
                    hDto.setStatusRelato(status.getStatusRelato());
                    hDto.setDataRegistro(status.getDataRegistro());
                    return hDto;
                }).toList();

        dto.setHistorico(historico);
        return dto;
    }

        @GetMapping("/codigo/{id}")
public CodigoRelatoDTO exibirCodigoRelato(@PathVariable Long id) {
    CadastroRelato relato = cadastroRelatoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Relato não encontrado: " + id));

    CodigoRelatoDTO dto = new CodigoRelatoDTO();
    dto.setId(relato.getId());
    dto.setCodigoRelato(relato.getCodigoRelato());

    return dto;
}



}

