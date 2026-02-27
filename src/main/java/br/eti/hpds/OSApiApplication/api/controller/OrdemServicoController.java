package br.eti.hpds.OSApiApplication.api.controller;

import br.eti.hpds.OSApiApplication.domain.dto.AtualizaStatusDTO;
import br.eti.hpds.OSApiApplication.domain.model.Cliente;
import br.eti.hpds.OSApiApplication.domain.model.OrdemServico;
import br.eti.hpds.OSApiApplication.domain.repository.ClienteRepository;
import br.eti.hpds.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.eti.hpds.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }

    /**
     * Busca as orderns de serviço de um determinado cliente.
     *
     * @param clienteID
     * @return
     */
    @GetMapping("/bycliente/{clienteID}")
    public ResponseEntity<List<OrdemServico>> buscarByCliente(@PathVariable Long clienteID) {

        Optional<List<OrdemServico>> optListaServico = ordemServicoService.findByCliente(clienteID);

        if (optListaServico.isPresent()) {
            return ResponseEntity.ok(optListaServico.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Carrega todas as ordens de serviço.
     *
     * @param ordemServicoID
     * @return
     */
    @GetMapping("/ordem-servico/{ordemServicoID}")
    public List<OrdemServico> buscar(@PathVariable Long ordemServicoID) {

        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoID);

        if (ordemServico.isPresent()) {
            return (List<OrdemServico>) ResponseEntity.ok(ordemServico.get());
        } else {
            return (List<OrdemServico>) ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ordem-servico")
    public ResponseEntity<List<OrdemServico>> findTodosBlablabla() {

        List<OrdemServico> ordemServico = ordemServicoService.findTodasOrdemServico();

        if (!ordemServico.isEmpty()) {
            return ResponseEntity.ok(ordemServico);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/ordem-servico/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizar(@Valid @PathVariable Long ordemServicoID,
            @RequestBody OrdemServico ordemServico) {
        if (!ordemServicoRepository.existsById(ordemServicoID)) {
            return ResponseEntity.notFound().build();
        }

        ordemServico.setId(ordemServicoID);
        ordemServico = ordemServicoService.salvar(ordemServico);
        return ResponseEntity.ok(ordemServico);
    }

    @DeleteMapping("/ordem-servico/{ordemServicoID}")
    public ResponseEntity<Void> excluir(@PathVariable Long ordemServicoID) {

        if (!ordemServicoRepository.existsById(ordemServicoID)) {
            return ResponseEntity.notFound().build();
        } else {

            ordemServicoService.excluir(ordemServicoID);
            return ResponseEntity.noContent().build();
        }
    }
    @PutMapping("/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(
            @PathVariable Long ordemServicoID,
            @Valid @RequestBody AtualizaStatusDTO statusDTO){
        
        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
                ordemServicoID,
                statusDTO.status());
        
        if (optOS.isPresent()){
            return ResponseEntity.ok(optOS.get());
        
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
