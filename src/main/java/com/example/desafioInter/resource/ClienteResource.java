package com.example.desafioInter.resource;

import com.example.desafioInter.entity.Cliente;
import com.example.desafioInter.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> inserirNovoCliente(@RequestBody Cliente cliente){
        Cliente novoCliente = clienteService.inserirNovoCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCliente}").buildAndExpand(novoCliente.getIdCliente()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{idCliente}")
    public ResponseEntity<Cliente> alterarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        Cliente alteraCliente = clienteService.alterarCliente(id, cliente);
        return ResponseEntity.ok().body(alteraCliente);
    }


    @GetMapping(value = "/idCliente/{idCliente}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id){
        Cliente buscaCliente = this.clienteService.buscarClientePorId(id);
        return ResponseEntity.ok().body(buscaCliente);
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<Cliente> buscarClientePorNome(@PathVariable String nome){
        Cliente buscaCliente = this.clienteService.buscarClientePorNome(nome);
        return ResponseEntity.ok().body(buscaCliente);
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable String cpf){
        Cliente buscaCliente = this.clienteService.buscarClientePorCpf(cpf);
        return ResponseEntity.ok().body(buscaCliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodosOsClientes(){
        List<Cliente> listaClientes = clienteService.buscarTodosOsClientes();
        return ResponseEntity.ok().body(listaClientes);
    }

    @DeleteMapping(value = "/{idCliente}")
    public ResponseEntity<Void> excluirClientePorId(@PathVariable Long id){
        clienteService.excluirClientePorId(id);
        return ResponseEntity.noContent().build();
    }

}
