package br.eti.hpds.OSApiApplication.domain.repository;

import br.eti.hpds.OSApiApplication.domain.model.Cliente;
import br.eti.hpds.OSApiApplication.domain.model.OrdemServico;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico , Long> {
    
    List<OrdemServico> findByCliente(Cliente cliente);
    List<OrdemServico> findByDescricao (String descricao);
    OrdemServico findByPreco (BigDecimal preco);
}
