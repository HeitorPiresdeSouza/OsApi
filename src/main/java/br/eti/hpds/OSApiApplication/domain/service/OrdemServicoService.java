package br.eti.hpds.OSApiApplication.domain.service;

import br.eti.hpds.OSApiApplication.domain.exception.DomainException;
import br.eti.hpds.OSApiApplication.domain.model.Cliente;
import br.eti.hpds.OSApiApplication.domain.model.OrdemServico;
import br.eti.hpds.OSApiApplication.domain.model.StatusOrdemServico;
import br.eti.hpds.OSApiApplication.domain.repository.ClienteRepository;
import br.eti.hpds.OSApiApplication.domain.repository.OrdemServicoRepository;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
