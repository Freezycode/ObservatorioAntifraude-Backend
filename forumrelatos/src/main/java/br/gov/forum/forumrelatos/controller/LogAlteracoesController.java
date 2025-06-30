package br.gov.forum.forumrelatos.controller;

import br.gov.forum.forumrelatos.dto.LogAlteracoesDTO;
import br.gov.forum.forumrelatos.model.LogAlteracoes;
import br.gov.forum.forumrelatos.repository.LogAlteracoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("log-alteracoes")
public class LogAlteracoesController {

    @Autowired
    private LogAlteracoesRepository logAlteracoesRepository;

    @GetMapping
    public List<LogAlteracoes> listarTodos() {
        return logAlteracoesRepository.findAll();
    }

    @GetMapping("/dto")
    public List<LogAlteracoesDTO> listarLogFormatado() {
        return logAlteracoesRepository.findAll().stream().map(log -> {
            LogAlteracoesDTO dto = new LogAlteracoesDTO();
            dto.setTabelaAfetada(log.getTabelaAfetada());
            dto.setRegistroId(log.getRegistroId());
            dto.setAcao(log.getAcao());
            //dto.setDadosAnteriores(log.getDadosAnteriores());
            dto.setDadosNovos(log.getDadosNovos());
            dto.setUsuarioId(log.getUsuario() != null ? log.getUsuario().getId() : null);
            return dto;
        }).toList();
    }

    @GetMapping("/{id}")
    public Optional<LogAlteracoes> buscarPorId(@PathVariable Long id) {
        return logAlteracoesRepository.findById(id);
    }

    @PostMapping
    public LogAlteracoes salvar(@RequestBody LogAlteracoes log) {
        return logAlteracoesRepository.save(log);
    }

    @PutMapping("/{id}")
    public LogAlteracoes atualizar(@PathVariable Long id, @RequestBody LogAlteracoes logAtualizado) {
        return logAlteracoesRepository.findById(id).map(log -> {
            log.setTabelaAfetada(logAtualizado.getTabelaAfetada());
            log.setRegistroId(logAtualizado.getRegistroId());
            log.setAcao(logAtualizado.getAcao());
            log.setDadosAnteriores(logAtualizado.getDadosAnteriores());
            log.setDadosNovos(logAtualizado.getDadosNovos());
            log.setDataAcao(logAtualizado.getDataAcao());
            log.setUsuario(logAtualizado.getUsuario());
            return logAlteracoesRepository.save(log);
        }).orElseThrow(() -> new RuntimeException("Log não encontrado: " + id));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        logAlteracoesRepository.deleteById(id);
    }
}
