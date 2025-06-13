package br.gov.forum.forumrelatos.controller;

import br.gov.forum.forumrelatos.dto.BuscaCodigoDTO;
import br.gov.forum.forumrelatos.dto.CadastroRelatoStatusDTO;
import br.gov.forum.forumrelatos.model.CadastroRelato;
import br.gov.forum.forumrelatos.repository.CadastroRelatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cadastro-relatos")
public class RelatoController {

    @Autowired
    private CadastroRelatoRepository cadastroRelatoRepository;

    /** get apenas para listar*/
    @GetMapping
    public List<CadastroRelato> listarTodos() {
        return cadastroRelatoRepository.findAll();
    }

    /** usei get mapping para buscart o relato*/
    @GetMapping("/{id}")
    public CadastroRelato buscarPorId(@PathVariable Long id) {
        return cadastroRelatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro relato não encontrado" + id));
    }

    /** post para enviarmos o relato*/
    @PostMapping
    public CadastroRelato salvar(@RequestBody CadastroRelato cadastroRelato) {
        return cadastroRelatoRepository.save(cadastroRelato);
    }

    /** put para atualizarmos os relatos*/
    @PutMapping("/{id}")
    public CadastroRelato atualizar(@PathVariable Long id, @RequestBody CadastroRelato novoRelato) {
        CadastroRelato relatoExistente = cadastroRelatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Esse relato não existe ou não encontrado" + id));
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

        return cadastroRelatoRepository.save(relatoExistente);
    }
    /** deletar os relatos conforme o id do usuario*/
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        CadastroRelato relato = cadastroRelatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao deletar o relato" + id));
        cadastroRelatoRepository.delete(relato);
    }
    /** get que busca pelo codigo do usuario seu relato e usei um dto para filtrar o que quero passar*/
    @GetMapping("/buscar")
    public BuscaCodigoDTO buscarPorCodigo(@RequestParam String codigoRelato) {
        CadastroRelato relato = cadastroRelatoRepository.findByCodigoRelato(codigoRelato)
                .orElseThrow(() -> new RuntimeException("Relato não encontrado com código: " + codigoRelato));

        BuscaCodigoDTO dto = new BuscaCodigoDTO();
        dto.setCodigoRelato(relato.getCodigoRelato());
        dto.setTipoRelato(relato.getTipoRelato());
        dto.setSistemaRelacionado(relato.getSistemaRelacionado());
        dto.setStatusRelato(relato.getStatusRelato());
        dto.setDataRegistro(relato.getDataRegistro());

        return dto;
    }
    /** get que consulta o nosso dto*/
    @GetMapping("status-consulta")
    public List<CadastroRelatoStatusDTO> listarStatusSimples() {
        List<CadastroRelato> relatos = cadastroRelatoRepository.findAll();

        List<CadastroRelatoStatusDTO> dtoList = relatos.stream().map(relato -> {
            CadastroRelatoStatusDTO dto = new CadastroRelatoStatusDTO();
            dto.setUsuarioId(relato.getUsuario().getId());
            dto.setCodigoRelato(relato.getCodigoRelato());
            dto.setTipoRelato(relato.getTipoRelato());
            dto.setSistemaRelacionado(relato.getSistemaRelacionado());
            dto.setDataRegistro(relato.getDataRegistro());
            return dto;
        }).toList();
        return dtoList;
    }
}


