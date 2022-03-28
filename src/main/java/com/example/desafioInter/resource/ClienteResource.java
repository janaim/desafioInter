package com.example.desafioInter.resource;

import com.example.desafioInter.entity.Cliente;
import com.example.desafioInter.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Clientes")
@CrossOrigin(origins = "*")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cliente")
    @ApiOperation(value = "Insere um novo cliente!")
    public ResponseEntity<Cliente> inserirNovoCliente(@RequestBody Cliente cliente){
        Cliente novoCliente = clienteService.inserirNovoCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCliente}").buildAndExpand(novoCliente.getIdCliente()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/cliente/{idCliente}")
    @ApiOperation(value = "Altera um cliente!")
    public ResponseEntity<Cliente> alterarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        Cliente alteraCliente = clienteService.alterarCliente(id, cliente);
        return ResponseEntity.ok().body(alteraCliente);
    }


    @GetMapping(value = "/cliente/idCliente/{idCliente}")
    @ApiOperation(value = "Busca um cliente por Id!")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id){
        Cliente buscaCliente = this.clienteService.buscarClientePorId(id);
        return ResponseEntity.ok().body(buscaCliente);
    }

    @GetMapping(value = "/cliente/nome/{nome}")
    @ApiOperation(value = "Busca um cliente por nome!")
    public ResponseEntity<Cliente> buscarClientePorNome(@PathVariable String nome){
        Cliente buscaCliente = this.clienteService.buscarClientePorNome(nome);
        return ResponseEntity.ok().body(buscaCliente);
    }

    @GetMapping(value = "/cliente/cpf/{cpf}")
    @ApiOperation(value = "Busca um cliente por Cpf!")
    public ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable String cpf){
        Cliente buscaCliente = this.clienteService.buscarClientePorCpf(cpf);
        return ResponseEntity.ok().body(buscaCliente);
    }

    @GetMapping("/cliente")
    @ApiOperation(value = "Retorna todos os clientes da base de dados!")
    public ResponseEntity<List<Cliente>> buscarTodosOsClientes(){
        List<Cliente> listaClientes = clienteService.buscarTodosOsClientes();
        return ResponseEntity.ok().body(listaClientes);
    }

    @DeleteMapping(value = "/cliente/{idCliente}")
    @ApiOperation(value = "Exclui um cliente por seu Id!")
    public ResponseEntity<Void> excluirClientePorId(@PathVariable Long id){
        clienteService.excluirClientePorId(id);
        return ResponseEntity.noContent().build();
    }

}
