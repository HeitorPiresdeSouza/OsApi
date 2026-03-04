package br.eti.hpds.OSApiApplication.api.controller;

import br.eti.hpds.OSApiApplication.domain.model.Comentario;
import br.eti.hpds.OSApiApplication.domain.repository.ComentarioRepository;
import br.eti.hpds.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.eti.hpds.OSApiApplication.domain.service.ComentarioService;
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

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comentario criar(@RequestBody Comentario comentario) {
        return comentario.criar(comentario);
    }

    @GetMapping("/comentario/{comentarioID}")
    public List<Comentario> buscarById(@PathVariable Long comentarioID) {

        Optional<Comentario> comentario = comentarioRepository.findById(comentarioID);

        if (comentario.isPresent()) {
            return (List<Comentario>) ResponseEntity.ok(comentario.get());
        } else {
            return (List<Comentario>) ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/comentario")
    public ResponseEntity<List<Comentario>> findTodos() {

        List<Comentario> comentario = comentarioService.findTodosComentarios();

        if (!comentario.isEmpty()) {
            return ResponseEntity.ok(comentario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/comentario/{comentarioID}")
    public ResponseEntity<Comentario> atualizar (@Valid @PathVariable Long comentarioID,
            @RequestBody Comentario comentario) {
        if (!comentarioRepository.existsById(comentarioID)) {
            return ResponseEntity.notFound().build();
        }
        
        comentario.setId(comentarioID);
        comentario = comentarioService.salvar(comentario);
        return ResponseEntity.ok(comentario);
    }
    
    @DeleteMapping("/comentario/{comentarioID}")
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
