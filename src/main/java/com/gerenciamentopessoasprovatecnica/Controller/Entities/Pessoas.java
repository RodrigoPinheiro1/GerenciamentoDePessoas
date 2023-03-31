package com.gerenciamentopessoasprovatecnica.Controller.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pessoas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy")
    private LocalDate dataNascimento;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "pessoas")
    private List<Endereco> enderecos = new ArrayList<>();


    public Pessoas(String nome, LocalDate dataNascimento, List<Endereco> enderecos) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
    }
}
