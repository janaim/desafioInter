package com.example.desafioInter.repository;

import com.example.desafioInter.entity.Cliente;
import com.example.desafioInter.entity.Empresa;
import com.example.desafioInter.entity.Investimento;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {

    List<Investimento> findAllByCliente(Cliente cliente);

    Optional<Investimento> findByCliente(Cliente cliente);

    List<Investimento> findAllByAcoesInvestidas(Empresa empresa);
}
