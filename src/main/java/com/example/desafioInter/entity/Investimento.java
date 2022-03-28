package com.example.desafioInter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Investimento implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idInvestimento;

    @Column(name = "nomeInvestimento", nullable = false)
    private String nomeInvestimento;

    @OneToOne(targetEntity = Cliente.class)
    private Cliente cliente;

    @Column(name = "dinheiroInvestido", nullable = false)
    private float dinheiroInvestido;

    @Column(name = "quantidadeEmpresasDiversificacao", nullable = false)
    private int quantidadeEmpresasDiversificacao;

    @OneToMany(targetEntity = Empresa.class)
    private List<List<Empresa>> acoesInvestidas;

}
