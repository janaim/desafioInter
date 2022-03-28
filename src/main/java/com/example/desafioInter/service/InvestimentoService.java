package com.example.desafioInter.service;

import com.example.desafioInter.entity.Cliente;
import com.example.desafioInter.entity.Empresa;
import com.example.desafioInter.entity.Investimento;
import com.example.desafioInter.repository.EmpresaRepository;
import com.example.desafioInter.repository.InvestimentoRepository;
import com.example.desafioInter.service.exceptions.ObjectNotFoundException;
import org.apache.commons.math3.util.Combinations;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.paukov.combinatorics3.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvestimentoService {

    @Autowired
    private InvestimentoRepository investimentoRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    public Investimento criarNovoInvestimentoPorCliente(Investimento investimento) {
        investimento.setIdInvestimento(null);
        return investimentoRepository.save(investimento);
    }

    public Investimento criarDiversificacaoInvestimentoSolicitado(Investimento investimento) {
        List<Empresa> listaAcoesStatusAtivo = empresaRepository.findAllByStatus(true);
        float somatoria = 0;
        int j = 0;
        float auxSoma = 0;
        List<List<Empresa>> empresasInvestidas = new ArrayList<>();

        List<List<Empresa>> combinacoesAcoes = Generator.combination(listaAcoesStatusAtivo)
                .simple(3)
                .stream()
                .collect(Collectors.<List<Empresa>>toList());

        for (List<Empresa> somaInvestimento : combinacoesAcoes) {
            for (int i = 0; i < investimento.getQuantidadeEmpresasDiversificacao(); i++) {
                somatoria = somaInvestimento.get(i).getPreco() + somatoria;
                somaInvestimento.get(i).setQuantidadeAcao(j++);

                if (somatoria > investimento.getDinheiroInvestido()) {
                    somatoria = somatoria - somaInvestimento.get(i).getPreco();
                    j = j - 1;
                    somaInvestimento.get(i).setQuantidadeAcao(j);

                    if (auxSoma < somatoria) {
                        auxSoma = somatoria;
                        empresasInvestidas.add(somaInvestimento);
                    }
                    somatoria = 0;
                    i = 0;
                    j = 0;
                }
            }
        }
        Collections.reverse(empresasInvestidas);

        for (int i = 0; i < investimento.getQuantidadeEmpresasDiversificacao(); i++){
            investimento.getAcoesInvestidas().add(Collections.singletonList(empresasInvestidas.get(0).get(i)));
        }

        return investimento;
    }


    public List<Investimento> buscarListaGeralDeInvestimentosPorCliente(Cliente cliente) {
        return investimentoRepository.findAllByCliente(cliente);
    }

    public Investimento buscarInvestimentoEspecificoPorCliente(Cliente cliente) {
        Optional<Investimento> obj = investimentoRepository.findByCliente(cliente);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Investimento não encontrado"));
    }

    public List<Investimento> buscarTotalDeInvestimentosPorEmpresa(Empresa empresa) {
        return investimentoRepository.findAllByAcoesInvestidas(empresa);
    }
}
