package br.eti.hpds.OSApiApplication.domain.service;

import br.eti.hpds.OSApiApplication.domain.dto.ComentarioDTO;
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

    public Comentario salvar(ComentarioDTO comentarioDTO) {

        Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(
                comentarioDTO.ordemServicoId());
        
        if (optOrdemServico.isEmpty()) {
            throw new DomainException("OrdemServico não existe");
        }

        // Existe Ordem de Serviço!
        Comentario comentario = new Comentario();
        comentario.setDescricao(comentarioDTO.descricao());
        comentario.setDataEnvio(LocalDateTime.now());
        comentario.setOrdemServico(optOrdemServico.get());
        return comentarioRepository.save(comentario);

    }
    
    public Comentario atualizar(Comentario comentario){ 
        return comentarioRepository.save(comentario);
    }

    public void excluir(Long comentarioID) {
        comentarioRepository.deleteById(comentarioID);
    }

    public List<Comentario> findTodosComentarios() {

        return comentarioRepository.findAll();

    }

}

/**
 *
 */
