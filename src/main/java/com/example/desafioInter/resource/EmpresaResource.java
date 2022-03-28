package com.example.desafioInter.resource;

import com.example.desafioInter.entity.Empresa;
import com.example.desafioInter.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaResource {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<Empresa> inserirNovaEmpresa(@RequestBody Empresa empresa){
        Empresa novaEmpresa = empresaService.inserirNovaEmpresa(empresa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idEmpresa}").buildAndExpand(novaEmpresa.getIdEmpresa()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/precoAcao/{precoAcao}")
    public ResponseEntity<Empresa> alterarPrecoAcao(@PathVariable Long id, @RequestBody float precoAcao){
        Empresa alteraPrecoAcao = empresaService.alterarPrecoAcao(id, precoAcao);
        return ResponseEntity.ok().body(alteraPrecoAcao);
    }

    @PutMapping(value = "/alteraStatus/{status}")
    public ResponseEntity<Empresa> alterarStatusEmpresa(@PathVariable Long id, @RequestBody boolean status){
        Empresa alteraStatusEmpresa = empresaService.alterarStatusEmpresa(id, status);
        return ResponseEntity.ok().body(alteraStatusEmpresa);
    }

    @GetMapping(value = "/buscaAcaoPorId/{idEmpresa}")
    public ResponseEntity<Empresa> buscarAcaoPorId(@PathVariable Long id){
        Empresa buscaAcao = this.empresaService.buscarAcaoPorId(id);
        return ResponseEntity.ok().body(buscaAcao);
    }

    @GetMapping(value = "/buscaAcaoNome/{nomeAcao}")
    public ResponseEntity<Empresa> buscarAcaoPorNome(@PathVariable String nomeAcao){
        Empresa buscaAcao = this.empresaService.buscarAcaoPorNome(nomeAcao);
        return ResponseEntity.ok().body(buscaAcao);
    }

    @GetMapping(value = "/buscaAcaoStatus/{status}")
    public ResponseEntity<List<Empresa>> buscarAcoesPorStatus(@PathVariable boolean status){
        List<Empresa> listaAcoes = this.empresaService.buscarAcoesPorStatus(status);
        return ResponseEntity.ok().body(listaAcoes);
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> buscarTodasAsAcoes(){
        List<Empresa> listaAcoes = empresaService.buscarTodasAsAcoes();
        return ResponseEntity.ok().body(listaAcoes);
    }

    @RequestMapping(value = "/nomeAcao/{nomeAcao}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluirEmpresaPorNomeAcao(@PathVariable String nomeAcao){
        empresaService.excluirEmpresaPorNomeAcao(nomeAcao);
        return ResponseEntity.noContent().build();
    }







}
