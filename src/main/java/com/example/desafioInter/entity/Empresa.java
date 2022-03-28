package com.example.desafioInter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Empresa implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEmpresa;

    @NotEmpty(message = "Por favor, preencha o nome da Empresa!" )
    @Column(name = "nomeEmpresa", nullable = false)
    private String nomeEmpresa;

    @NotEmpty(message = "Por favor, preencha o nome da Ação!" )
    @Column(name = "nomeAcao", nullable = false)
    private String nomeAcao;

    @NotEmpty(message = "Por favor, preencha o ticker da Ação!")
    @Column(name = "ticker")
    private String ticker;

    @NotNull(message = "Por favor, preencha o preço da Ação!")
    @Column(name = "preco", nullable = false)
    private float preco;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "quanrtidadeAcao")
    private int quantidadeAcao;


}
