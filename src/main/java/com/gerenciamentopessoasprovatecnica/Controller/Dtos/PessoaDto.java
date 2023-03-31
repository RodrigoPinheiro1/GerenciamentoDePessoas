package com.gerenciamentopessoasprovatecnica.Controller.Dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PessoaDto {

    private Long id ;

    @NotBlank
    @NotNull
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    @Valid
    private List<EnderecoDto> enderecos;



}
