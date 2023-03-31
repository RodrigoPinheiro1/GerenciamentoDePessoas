package com.gerenciamentopessoasprovatecnica.Controller.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastroEnderecoDto {


    private Long id;
    @NotBlank
    @NotNull
    private String logradouro;
    @NotBlank
    @NotNull
    private String cep;

    private boolean enderecoPrincipal;
    @NotBlank
    @NotNull
    private String numero;
    @NotBlank
    @NotNull
    private String cidade;

    @NotNull
    private Long pessoasId;


    public CadastroEnderecoDto(String logradouro, String cep, boolean enderecoPrincipal, String numero, String cidade, Long pessoasId) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.enderecoPrincipal = enderecoPrincipal;
        this.numero = numero;
        this.cidade = cidade;
        this.pessoasId = pessoasId;
    }
}
