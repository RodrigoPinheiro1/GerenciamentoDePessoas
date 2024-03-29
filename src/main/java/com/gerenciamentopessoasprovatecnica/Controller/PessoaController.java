package com.gerenciamentopessoasprovatecnica.Controller;

import com.gerenciamentopessoasprovatecnica.Controller.Dtos.CadastroEnderecoDto;
import com.gerenciamentopessoasprovatecnica.Controller.Dtos.EnderecoDto;
import com.gerenciamentopessoasprovatecnica.Controller.Dtos.PessoaDto;
import com.gerenciamentopessoasprovatecnica.Controller.service.PessoaServiceImplements;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaServiceImplements service;


    @PostMapping("/endereco")
    public ResponseEntity<CadastroEnderecoDto> cadastrarEnderecoParaPessoa (@RequestBody @Valid
                                                                    CadastroEnderecoDto dto, UriComponentsBuilder builder) {


        CadastroEnderecoDto enderecoDto = service.cadastrarEnderecoParaPessoa(dto);


        URI uri = builder.path("/pessoas/{id}").buildAndExpand(enderecoDto.getId()).toUri();
        return ResponseEntity.created(uri).body(enderecoDto);

    }

    @PostMapping
    public ResponseEntity<PessoaDto> cadastroPessoa(@RequestBody @Valid PessoaDto dto, UriComponentsBuilder builder) {


        PessoaDto cadastro = service.cadastro(dto);

        URI uri = builder.path("/pessoas/{id}").buildAndExpand(cadastro.getId()).toUri();
        return ResponseEntity.created(uri).body(cadastro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> atualizaPessoa(@PathVariable @NotNull Long id, @RequestBody @Valid  PessoaDto dto) {

        PessoaDto atualizacao = service.atualizacao(dto, id);

        return ResponseEntity.ok().body(atualizacao);
    }

    @GetMapping
    public Page<PessoaDto> listar(Pageable pageable) {

        return service.listar(pageable);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> listarPorId(@PathVariable Long id) {

        PessoaDto dto = service.buscarPorId(id);

        return ResponseEntity.ok(dto);

    }


}
