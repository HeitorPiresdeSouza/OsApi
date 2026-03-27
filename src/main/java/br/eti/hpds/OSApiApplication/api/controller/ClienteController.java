package br.eti.hpds.OSApiApplication.api.controller;

import br.eti.hpds.OSApiApplication.domain.model.Cliente;
import br.eti.hpds.OSApiApplication.domain.repository.ClienteRepository;
import br.eti.hpds.OSApiApplication.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")

    @Operation(summary = "Lista todos os clientes", description = "Retorna a lista de todos os clientes da base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")}
    )
    public List<Cliente> listas() {
        return clienteRepository.findAll();
    }

    @GetMapping("/clientes/{clienteID}")

    @Operation(summary = "Lista um cliente por ID", description = "Retorna um cliente por determinado ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")}
    )

    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID) {

        Optional<Cliente> cliente = clienteRepository.findById(clienteID);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/clientes")
    
    @Operation(summary = "Publica um determinado cliente", description = "Publicação de um cliente na base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully posted"),
        @ApiResponse(responseCode = "422", description = "The format is correct, but the data failed the business rule")}
    )
    
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        
        return clienteService.salvar(cliente);
    }

    @PutMapping("/clientes/{clienteID}")
    
    @Operation(summary = "Atualiza um determinado cliente", description = "Atualização de um cliente na base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated"),
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")}
    )
    
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteID,
            @RequestBody Cliente cliente) {

        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteID);
        cliente = clienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{clienteID}")
    
    @Operation(summary = "Deleta um determinado cliente", description = "Exclui um cliente através do ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")}
    )
    
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID) {

        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }

        clienteService.excluir(clienteID);
        return ResponseEntity.noContent().build();
    }
}
