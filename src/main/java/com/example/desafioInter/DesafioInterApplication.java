package com.example.desafioInter;

import com.example.desafioInter.entity.Cliente;
import com.example.desafioInter.entity.Empresa;
import com.example.desafioInter.entity.Investimento;
import com.example.desafioInter.repository.ClienteRepository;
import com.example.desafioInter.repository.EmpresaRepository;
import com.example.desafioInter.repository.InvestimentoRepository;
import org.apache.commons.math3.util.Combinations;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.hibernate.collection.internal.PersistentList;
import org.modelmapper.ModelMapper;
import org.paukov.combinatorics3.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootApplication
public class DesafioInterApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private InvestimentoRepository investimentoRepository;

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(DesafioInterApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Cliente clienteExemplo = new Cliente(null, "Lucas da Silva", "lucas@email.com", "12345678900");
		Cliente clienteExemplo1 = new Cliente(null, "Maria de mara", "maria@email.com", "32165498700");

		Empresa empresaExemplo = new Empresa(null, "Banco Inter S.A.", "Inter", "BIDI11", (float) 66.51, true, 0);
		Empresa empresaExemplo1 = new Empresa(null, "Magazine Luiza S.A.", "Magazine Luiza", "MGLU3", (float) 18.80, true, 0);
		Empresa empresaExemplo2 = new Empresa(null, "Sul América S.A", "Sulamérica", "SULA11", (float) 28.26, true, 0);
		Empresa empresaExemplo3 = new Empresa(null, "Engie Brasil Serviços de Energia Ltda.", "Engie", "EGIE3", (float) 38.30, true, 0);
		Empresa empresaExemplo4 = new Empresa(null, "Cvc Brasil Operadora e Agencia de Viagens S.A", "CVC", "CVCB3", (float) 20.87, true, 0);
		Empresa empresaExemplo5 = new Empresa(null, "Lojas Renner S.A.", "Renner", "LREN3", (float) 36.95, true, 0);
		Empresa empresaExemplo6 = new Empresa(null, "Marisa Lojas S.A", "Marisa", "AMAR3", (float) 6.30, true, 0);


		clienteRepository.saveAll(Arrays.asList(clienteExemplo, clienteExemplo1));
		empresaRepository.saveAll(Arrays.asList(empresaExemplo, empresaExemplo1, empresaExemplo2, empresaExemplo3,
				empresaExemplo4, empresaExemplo5, empresaExemplo6));

//		List<Empresa> listaEmpresa = new ArrayList<>();
//		List<List<Empresa>> investimentoF = new ArrayList<>();
//
//		Investimento investimento = new Investimento(null, "Investimento Teste", clienteExemplo, (float)100, 3, investimentoF);
//
//		investimentoRepository.save((investimento));
//
//		listaEmpresa = empresaRepository.findAllByStatus(true);
//
//
//		//retorna a quantidade de combinações possíveis
//		float somatoria = 0;
//		int j = 0;
//		float auxSoma = 0;
//		boolean auxQauntidadeAcoes = false;
//		List<Float> somatorias = new ArrayList<>();
//		List<List<Empresa>> empresasInvestidas = new ArrayList<>();
//
//
//		List<List<Empresa>> combinacoesAcoes = Generator.combination(listaEmpresa)
//				.simple(3)
//				.stream()
//				.collect(Collectors.<List<Empresa>>toList());
//
//		for (List<Empresa> somaInvestimento: combinacoesAcoes) {
//			for(int i = 0; i < investimento.getQuantidadeEmpresasDiversificacao(); i++) {
//				somatoria = somaInvestimento.get(i).getPreco() + somatoria;
//				somaInvestimento.get(i).setQuantidadeAcao(j++);
//				if(somatoria > investimento.getDinheiroInvestido()){
//					somatoria = somatoria - somaInvestimento.get(i).getPreco();
//					j = j-1;
//					somaInvestimento.get(i).setQuantidadeAcao(j);
//					//somatorias.add(somatoria);
//					if(auxSoma < somatoria){
//						auxSoma = somatoria;
//						empresasInvestidas.add(somaInvestimento);
//					}
//					somatoria = 0;
//					i = 0;
//					j = 0;
//				} else if (investimento.getQuantidadeEmpresasDiversificacao() == i || somatoria == investimento.getDinheiroInvestido()){
//					somatorias.add(somatoria);
//					somatoria = 0;
//					i = 0;
//				}
//			}
//
//		}
//
//		Collections.reverse(empresasInvestidas);
//		List<List<Empresa>> investimentoFinal = new ArrayList<>();
//
//		for (int i = 0; i < investimento.getQuantidadeEmpresasDiversificacao(); i++){
//			investimentoFinal.add(Collections.singletonList(empresasInvestidas.get(0).get(i)));
//			investimento.getAcoesInvestidas().add(Collections.singletonList(empresasInvestidas.get(0).get(i)));
//		}

//		for (int i = 0; i < empresasInvestidas.size(); i++){
//			for (int k = 0; k < investimento.getQuantidadeEmpresasDiversificacao(); k++) {
//				if(k == investimento.getQuantidadeEmpresasDiversificacao() && !auxQauntidadeAcoes){
//					investimentoFinal.add(Collections.singletonList(empresasInvestidas.get(0).get(i)));
//					break;
//				} else if(k == investimento.getQuantidadeEmpresasDiversificacao() && auxQauntidadeAcoes){
//					auxQauntidadeAcoes = false;
//				}
//				if(empresasInvestidas.get(i).get(k).getQuantidadeAcao() < 1){
//					auxQauntidadeAcoes = true;
//				}

//
//			}
//		}
	}


}
