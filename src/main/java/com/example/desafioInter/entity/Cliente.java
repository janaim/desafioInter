package com.example.desafioInter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCliente;

    @NotEmpty(message = "Por favor, preencha o nome do cliente!" )
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotEmpty(message = "Por favor, preencha o email do cliente!")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Por favor, preencha o CPF do cliente!")
    @Column(name = "cpf", nullable = false)
    private String cpf;
}
