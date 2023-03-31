package com.gerenciamentopessoasprovatecnica.Controller.Dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PessoaDto {

    private Long id ;

    @NotBlank
    @NotNull
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    @Valid
    private List<EnderecoDto> enderecos;


    public PessoaDto(String nome, LocalDate dataNascimento, List<EnderecoDto> enderecos) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
    }
}
