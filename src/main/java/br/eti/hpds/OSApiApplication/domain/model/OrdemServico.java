package br.eti.hpds.OSApiApplication.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class OrdemServico {
    
    @Schema(name = "Ordem de Servico ID", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Schema(name = "Nome Cliente", example = "heitor", required = false)
    @ManyToOne
    private Cliente cliente;
    
    @Schema(name = "Comentarios", example = "Concerto computador 222", required = false)
    @OneToMany(mappedBy = "ordemServico")
    private List<Comentario> comentarios;
    
    @Schema(name = "Descrição", example = "O material foi pra concerto", required = false)
    private String descricao;
    
    @Schema(name = "Preço", example = "R$120,00", required = true)
    private BigDecimal preco;
    
    @Schema(name = "Status", example = "ABERTA", required = false)
    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;
    
    @Schema(name = "Data de Abertura", example = "12-02-2026", required = false)
    private LocalDateTime dataAbertura;
    
    @Schema(name = "Data de Finalização", example = "20-02-2026", required = false)
    private LocalDateTime dataFinalizacao;
    
    

    public OrdemServico() {
    }

    
    public OrdemServico(Cliente cliente, String descricao, BigDecimal preco) {
        this.cliente = cliente;
        this.descricao = descricao;
        this.preco = preco;
    }
    
   
    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdemServico other = (OrdemServico) obj;
        return Objects.equals(this.id, other.id);
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    
}



    

