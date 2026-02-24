package br.eti.hpds.OSApiApplication.api.controller;

import br.eti.hpds.OSApiApplication.domain.model.OrdemServico;
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

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }
    
    @GetMapping("/ordem-servico")
    public List<OrdemServico> buscar(@PathVariable Long ordemServicoID) {
        
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoID);

        if (ordemServico.isPresent()) {
            return (List<OrdemServico>) ResponseEntity.ok(ordemServico.get());
        } else {
            return (List<OrdemServico>) ResponseEntity.notFound().build();
        }
    }

    
    @PutMapping("ordem-servico")
    public ResponseEntity<OrdemServico> atualizar (@Valid @PathVariable Long ordemServicoID,
                                                   @RequestBody OrdemServico ordemServico){
        if(!ordemServicoRepository.existsById(ordemServicoID)){
            return ResponseEntity.notFound().build();
        }
        
        ordemServico.setId(ordemServicoID);
        ordemServico = ordemServicoService.salvar(ordemServico);
        return ResponseEntity.ok(ordemServico);
    }
    
    @DeleteMapping("/ordem-servico")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID){
        
        if(!ordemServicoRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        
        ordemServicoService.excluir(clienteID);
        return ResponseEntity.noContent().build();
    }
}
