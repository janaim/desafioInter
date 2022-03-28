package com.example.desafioInter.resource;

import com.example.desafioInter.entity.Cliente;
import com.example.desafioInter.entity.Empresa;
import com.example.desafioInter.entity.Investimento;
import com.example.desafioInter.service.InvestimentoService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/investimento")
public class InvestimentoResource {

    @Autowired
    private InvestimentoService investimentoService;

    @PostMapping
    public ResponseEntity<Investimento> briarNovoInvestimentoPorCliente(
            @RequestBody Investimento investimento) {
        Investimento novoInvestimento =
                investimentoService.criarNovoInvestimentoPorCliente(investimento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idInvestimento}")
                        .buildAndExpand(novoInvestimento.getIdInvestimento()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "investimento/clienteLista/{cliente}",
            method = RequestMethod.GET, params = "filter")
    public ResponseEntity<List<Investimento>>
    buscarListaGeralDeInvestimentosPorCliente(Cliente cliente) {
        List<Investimento> listaGeralInvestimentosCliente =
                this.investimentoService.buscarListaGeralDeInvestimentosPorCliente(cliente);
        return ResponseEntity.ok().body(listaGeralInvestimentosCliente);
    }

    @GetMapping("investimento/{empresa}")
    public ResponseEntity<List<Investimento>>
        buscarTotalDeInvestimentosPorEmpresa(Empresa empresa) {
        List<Investimento> listaGeralInvestimentosCliente =
                this.investimentoService.buscarTotalDeInvestimentosPorEmpresa(empresa);
        return ResponseEntity.ok().body(listaGeralInvestimentosCliente);
    }

    @RequestMapping(value = "investimento/investimentoCliente/{cliente}"
            , method = RequestMethod.GET, params = "filter")
    public ResponseEntity<Investimento> buscarInvestimentoEspecificoPorCliente(Cliente cliente) {
        Investimento investimentoCliente =
                this.investimentoService.buscarInvestimentoEspecificoPorCliente(cliente);
        return ResponseEntity.ok().body(investimentoCliente);
    }
}
