package br.eti.hpds.OSApiApplication.domain.service;

import br.eti.hpds.OSApiApplication.domain.exception.DomainException;
import br.eti.hpds.OSApiApplication.domain.model.Comentario;
import br.eti.hpds.OSApiApplication.domain.model.OrdemServico;
import br.eti.hpds.OSApiApplication.domain.model.StatusOrdemServico;
import br.eti.hpds.OSApiApplication.domain.repository.ClienteRepository;
import br.eti.hpds.OSApiApplication.domain.repository.ComentarioRepository;
import br.eti.hpds.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {
    
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Comentario criar (Comentario comentario){
        comentario.setDescricao(comentario.getDescricao());
        comentario.setDataEnvio(LocalDateTime.now());
        
        return comentarioRepository.save(comentario);
    }
    
    public void excluir(Long comentarioID){
        comentarioRepository.deleteById(comentarioID);
    }
    
     public List<Comentario> findTodosComentarios(){
        
        return comentarioRepository.findAll();
        
    }
     
     public Comentario salvar (Comentario comentario) {
         Optional<OrdemServico> ordemServicoExistente = ordemServicoRepository.findById(comentario.getId());
         
         if(!ordemServicoExistente.isPresent()) {
             throw new DomainException("Cliente não existe");
         } else {
             return comentarioRepository.save(comentario);
         }
     }
}

/**
 *
 */