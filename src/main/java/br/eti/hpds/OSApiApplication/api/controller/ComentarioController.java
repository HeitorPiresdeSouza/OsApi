package br.eti.hpds.OSApiApplication.api.controller;

import br.eti.hpds.OSApiApplication.domain.dto.ComentarioDTO;
import br.eti.hpds.OSApiApplication.domain.model.Comentario;
import br.eti.hpds.OSApiApplication.domain.repository.ComentarioRepository;
import br.eti.hpds.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.eti.hpds.OSApiApplication.domain.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ComentarioService comentarioService;

    /**
     * Cria um comentário.
     * @param comentario
     * @return 
     */
    @PostMapping
    
    @Operation(summary = "Publica um determinado comentario", description = "Publicação de um comentario na base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully posted"),
        @ApiResponse(responseCode = "422", description = "The format is correct, but the data failed the business rule")}
    )
    
    @ResponseStatus(HttpStatus.CREATED)
    public Comentario criar(@RequestBody ComentarioDTO comentarioDTO) {
        return comentarioService.salvar(comentarioDTO);
    }

    @GetMapping("/{comentarioID}")
    
    @Operation(summary = "Lista os comentarios por ID", description = "Retorna os comentários de determinada OS")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")}
    )
    
    public ResponseEntity<Comentario> buscarById(@PathVariable Long comentarioID) {

        Optional<Comentario> comentario = comentarioRepository.findById(comentarioID);

        if (comentario.isPresent()) {
            return ResponseEntity.ok(comentario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    
    @Operation(summary = "Lista todos comentarios", description = "Retorna a lista de todos os comentarios da base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")}
    )
    
    public ResponseEntity<List<Comentario>> findTodos() {

        List<Comentario> comentario = comentarioService.findTodosComentarios();

        if (!comentario.isEmpty()) {
            return ResponseEntity.ok(comentario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{comentarioID}")
    
    @Operation(summary = "Atualiza um determinado comentario", description = "Atualização de um comentario na base de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated"),
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")}
    )
    
    public ResponseEntity<Comentario> atualizar (@PathVariable Long comentarioID,
            @RequestBody ComentarioDTO comentarioDTO) {
        
        Optional<Comentario> optComentario = comentarioRepository.findById(comentarioID);
        
        if (optComentario.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        
        Comentario comentario = optComentario.get();
        comentario.setDescricao(comentarioDTO.descricao());
        
        comentarioService.atualizar(comentario);
        
        return ResponseEntity.ok(comentario);
    
    }
//    
    @DeleteMapping("/{comentarioID}")
    
    @Operation(summary = "Deleta um determinado comentario", description = "Exclui um comentario através do ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")}
    )
    
    public ResponseEntity<Void> excluir(@PathVariable Long comentarioID) {
        
        if (!comentarioRepository.existsById(comentarioID)) {
            return ResponseEntity.notFound().build();
        } else {
            comentarioService.excluir(comentarioID);
            return ResponseEntity.noContent().build();
        }
    }
}
/**
 *
 */
