package com.example.desafioInter.resource;

import com.example.desafioInter.entity.Empresa;
import com.example.desafioInter.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Empresas")
@CrossOrigin(value = "*")
public class EmpresaResource {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/empresa")
    @ApiOperation(value = "Insere uma nova empresa!")
    public ResponseEntity<Empresa> inserirNovaEmpresa(@RequestBody Empresa empresa){
        Empresa novaEmpresa = empresaService.inserirNovaEmpresa(empresa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idEmpresa}").buildAndExpand(novaEmpresa.getIdEmpresa()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/empresa/precoAcao/{precoAcao}")
    @ApiOperation(value = "Altera o preço da ação!")
    public ResponseEntity<Empresa> alterarPrecoAcao(@PathVariable Long id, @RequestBody float precoAcao){
        Empresa alteraPrecoAcao = empresaService.alterarPrecoAcao(id, precoAcao);
        return ResponseEntity.ok().body(alteraPrecoAcao);
    }

    @PutMapping(value = "/empresa/alteraStatus/{status}")
    @ApiOperation(value = "Altera o status da empresa!")
    public ResponseEntity<Empresa> alterarStatusEmpresa(@PathVariable Long id, @RequestBody boolean status){
        Empresa alteraStatusEmpresa = empresaService.alterarStatusEmpresa(id, status);
        return ResponseEntity.ok().body(alteraStatusEmpresa);
    }

    @GetMapping(value = "/empresa/buscaAcaoPorId/{idEmpresa}")
    @ApiOperation(value = "Busca uma ação a partir de seu Id!")
    public ResponseEntity<Empresa> buscarAcaoPorId(@PathVariable Long id){
        Empresa buscaAcao = this.empresaService.buscarAcaoPorId(id);
        return ResponseEntity.ok().body(buscaAcao);
    }

    @GetMapping(value = "/empresa/buscaAcaoNome/{nomeAcao}")
    @ApiOperation(value = "Busca uma ação a partir de seu nome!")
    public ResponseEntity<Empresa> buscarAcaoPorNome(@PathVariable String nomeAcao){
        Empresa buscaAcao = this.empresaService.buscarAcaoPorNome(nomeAcao);
        return ResponseEntity.ok().body(buscaAcao);
    }

    @GetMapping(value = "/empresa/buscaAcaoStatus/{status}")
    @ApiOperation(value = "Busca uma ação a partir de seu status!")
    public ResponseEntity<List<Empresa>> buscarAcoesPorStatus(@PathVariable boolean status){
        List<Empresa> listaAcoes = this.empresaService.buscarAcoesPorStatus(status);
        return ResponseEntity.ok().body(listaAcoes);
    }

    @GetMapping("/empresa")
    @ApiOperation(value = "Busca todas as ações!")
    public ResponseEntity<List<Empresa>> buscarTodasAsAcoes(){
        List<Empresa> listaAcoes = empresaService.buscarTodasAsAcoes();
        return ResponseEntity.ok().body(listaAcoes);
    }

    @RequestMapping(value = "/empresa/nomeAcao/{nomeAcao}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Exclui uma ação a partir de seu nome!")
    public ResponseEntity<Void> excluirEmpresaPorNomeAcao(@PathVariable String nomeAcao){
        empresaService.excluirEmpresaPorNomeAcao(nomeAcao);
        return ResponseEntity.noContent().build();
    }







}
