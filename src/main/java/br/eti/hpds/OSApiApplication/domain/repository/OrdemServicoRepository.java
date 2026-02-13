package br.eti.hpds.OSApiApplication.domain.repository;

import br.eti.hpds.OSApiApplication.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico , Long> {
    
}
