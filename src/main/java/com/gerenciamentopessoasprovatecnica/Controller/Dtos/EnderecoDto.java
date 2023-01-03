package com.gerenciamentopessoasprovatecnica.Controller.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {

    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;


}
