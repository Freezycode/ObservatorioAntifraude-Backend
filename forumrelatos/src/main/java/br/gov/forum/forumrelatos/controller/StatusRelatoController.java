package br.gov.forum.forumrelatos.controller;

import br.gov.forum.forumrelatos.dto.StatusRelatoHistoricoDTO;
import br.gov.forum.forumrelatos.model.StatusRelato;
import br.gov.forum.forumrelatos.repository.StatusRelatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("status-relato")
public class StatusRelatoController {

    @Autowired
    private StatusRelatoRepository statusRelatoRepository;

    @GetMapping
    public List<StatusRelato> listarTodos() {
        return statusRelatoRepository.findAll();
    }
    @PostMapping
    public StatusRelato salvar(@RequestBody StatusRelato statusRelato) {
        return statusRelatoRepository.save(statusRelato);
    }
    @GetMapping("analise")
    public List<StatusRelatoHistoricoDTO> listarHistorico() {
        List<StatusRelato> statusList = statusRelatoRepository.findAll();

        return statusList.stream().map(status -> {
            StatusRelatoHistoricoDTO dto = new StatusRelatoHistoricoDTO();
            dto.setStatusRelato(status.getStatusRelato());
            dto.setDataRegistro(status.getDataRegistro());
            return dto;
        }).toList();
    }
}

