
package br.eti.hpds.OSApiApplication.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Aluno
 */
@Entity
public class Cliente {
    
    @Schema(name = "Cliente ID", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Schema(name = "Nome Cliente", example = "heitor", required = false)
    @NotBlank
    @Size(max = 60)
    private String nome;
    
    @Schema(name = "Email", example = "heitor@email.com", required = false)
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    
    @Schema(name = "Telefone", example = "(12)99999-9999", required = true)
    @NotBlank
    @Size(max = 20)
    @Column(name = "telefone")
    private String fone;

    public Cliente() {
    }

    
    
    public Cliente(long id, String nome, String email, String fone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getFone() {
        return fone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Cliente other = (Cliente) obj;
        return this.id == other.id;
    }
    
    
    
}
