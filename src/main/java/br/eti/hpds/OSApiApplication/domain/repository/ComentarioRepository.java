package br.eti.hpds.OSApiApplication.domain.repository;

import br.eti.hpds.OSApiApplication.domain.model.Cliente;
import br.eti.hpds.OSApiApplication.domain.model.Comentario;
import br.eti.hpds.OSApiApplication.domain.model.OrdemServico;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
    List<Comentario> findByDescricaoContaining (String descricao);
    List<Comentario> findByDataEnvio (LocalDateTime dataEnvio);
}
