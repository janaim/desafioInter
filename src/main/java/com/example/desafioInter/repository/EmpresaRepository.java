package com.example.desafioInter.repository;

import com.example.desafioInter.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {


    Optional<Empresa> findByNomeAcao(String nomeAcao);

    List<Empresa> findAllByStatus(boolean status);

    void deleteByNomeAcao(String nomeAcao);
}
