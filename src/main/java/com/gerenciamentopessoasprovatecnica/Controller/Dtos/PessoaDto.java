package com.gerenciamentopessoasprovatecnica.Controller.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PessoaDto {

    private UUID id ;
    private String nome;

    private LocalDate dataNascimento;

    private List<EnderecoDto> enderecos;



}
