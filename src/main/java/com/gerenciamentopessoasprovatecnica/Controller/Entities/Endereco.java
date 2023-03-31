package com.gerenciamentopessoasprovatecnica.Controller.Entities;

import com.gerenciamentopessoasprovatecnica.Controller.Dtos.EnderecoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean enderecoPrincipal;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;

    @ManyToOne
    private Pessoas pessoas;

    public Endereco(String logradouro, String cep, String numero, String cidade) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
    }
}
