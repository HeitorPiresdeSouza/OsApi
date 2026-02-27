package br.eti.hpds.OSApiApplication.api.controller;

import br.eti.hpds.OSApiApplication.domain.model.Comentario;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comentario criar(@RequestBody Comentario comentario) {
        return comentario.criar(comentario);
    }
}
