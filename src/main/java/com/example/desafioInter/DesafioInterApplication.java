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

	}
}
