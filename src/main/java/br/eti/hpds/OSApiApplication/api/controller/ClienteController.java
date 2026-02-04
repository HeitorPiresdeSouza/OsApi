
package br.eti.hpds.OSApiApplication.api.controller;

import br.eti.hpds.OSApiApplication.domain.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController

public class ClienteController {
    
    List<Cliente> listaClientes;
    
    @GetMapping("/clientes")
    public List<Cliente> listas() {
        
        listaClientes = new ArrayList<Cliente>();
        listaClientes.add(new Cliente(1,"KGe", "kge@teste.com", "11-99999-9999"));
        listaClientes.add(new Cliente(1,"maria", "maria@teste.com", "11-88888-8888"));
        listaClientes.add(new Cliente(1,"joao", "joao@teste.com", "11-77777-7777"));
        
        return listaClientes;
}

}
