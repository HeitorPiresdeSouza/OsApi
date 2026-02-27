package br.eti.hpds.OSApiApplication.domain.service;

import br.eti.hpds.OSApiApplication.domain.dto.AtualizaStatusDTO;
import br.eti.hpds.OSApiApplication.domain.exception.DomainException;
import br.eti.hpds.OSApiApplication.domain.model.Cliente;
import br.eti.hpds.OSApiApplication.domain.model.OrdemServico;
import br.eti.hpds.OSApiApplication.domain.model.StatusOrdemServico;
import br.eti.hpds.OSApiApplication.domain.repository.ClienteRepository;
import br.eti.hpds.OSApiApplication.domain.repository.OrdemServicoRepository;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class OrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public OrdemServico criar(OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        
        return ordemServicoRepository.save(ordemServico);
    }
     
    public OrdemServico salvar (OrdemServico ordemServico) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(ordemServico.getId());
        
        if (!clienteExistente.isPresent()){
            throw new DomainException("Cliente não existe");
    }else{
            return ordemServicoRepository.save(ordemServico);
        }
    
}
    
    public void excluir (Long ordemServicoID){
        ordemServicoRepository.deleteById(ordemServicoID);
    }

    public Optional<List<OrdemServico>> findByCliente(Long clienteID) {
        
        Optional<Cliente> clienteExistente = clienteRepository.findById(clienteID);
        Optional<List<OrdemServico>> ordemServicoList = null;
        
        // Captura o cliente no clienteRepository (verifica se existe)
        if(clienteExistente.isPresent()){
            // Busca no ordem de servico o objeto cliente.
            
            ordemServicoList = Optional.of( ordemServicoRepository.findByCliente(clienteExistente.get())) ;
        }
        
        return ordemServicoList;
    
    }
    
    public List<OrdemServico> findTodasOrdemServico(){
        
        return ordemServicoRepository.findAll();
        
    }
    
    public Optional<OrdemServico> atualizaStatus(Long ordemServicoID, StatusOrdemServico status){
        
        Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(ordemServicoID);
        
        if (optOrdemServico.isPresent()) {
            
            OrdemServico ordemServico = optOrdemServico.get();
            
            if (ordemServico.getStatus()==StatusOrdemServico.ABERTA
                    && status != StatusOrdemServico.ABERTA) {
            
                ordemServico.setStatus(status);
                ordemServico.setDataFinalizacao(LocalDateTime.now());
                ordemServicoRepository.save(ordemServico);
                return Optional.of(ordemServico);
                
            } else {
            
                return Optional.empty();
            }
        } else {
            throw new DomainException("Não existe OS com o id " + ordemServicoID);
        }
    }
}
