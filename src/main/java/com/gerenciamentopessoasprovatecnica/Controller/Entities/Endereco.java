package com.gerenciamentopessoasprovatecnica.Controller.Entities;

import com.gerenciamentopessoasprovatecnica.Controller.Dtos.EnderecoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;


}
