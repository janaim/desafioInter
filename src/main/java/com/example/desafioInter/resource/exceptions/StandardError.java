package com.example.desafioInter.resource.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError {

    private Integer status;
    private Long timeStanp;
    private String message;

}
