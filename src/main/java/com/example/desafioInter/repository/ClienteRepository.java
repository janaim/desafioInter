package com.example.desafioInter.repository;

import com.example.desafioInter.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    Optional<Cliente> findByNome(String nome);

    Optional<Cliente> findByCpf(String cpf);
}
