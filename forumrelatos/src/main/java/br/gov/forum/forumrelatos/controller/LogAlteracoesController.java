package br.gov.forum.forumrelatos.controller;

import br.gov.forum.forumrelatos.model.LogAlteracoes;
import br.gov.forum.forumrelatos.repository.LogAlteracoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("log-alteracoes")
public class LogAlteracoesController {

    @Autowired
    private LogAlteracoesRepository logAlteracoesRepository;

    @GetMapping
    public List<LogAlteracoes> listarTodos() {
        return logAlteracoesRepository.findAll();
    }

    @PostMapping
    public LogAlteracoes salvar(@RequestBody LogAlteracoes logAlteracoes) {
        return logAlteracoesRepository.save(logAlteracoes);
    }
}
