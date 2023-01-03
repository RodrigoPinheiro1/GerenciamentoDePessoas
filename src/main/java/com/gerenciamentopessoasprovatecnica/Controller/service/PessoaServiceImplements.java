package com.gerenciamentopessoasprovatecnica.Controller.service;

import com.gerenciamentopessoasprovatecnica.Controller.Dtos.EnderecoDto;
import com.gerenciamentopessoasprovatecnica.Controller.Dtos.PessoaDto;
import com.gerenciamentopessoasprovatecnica.Controller.Entities.Endereco;
import com.gerenciamentopessoasprovatecnica.Controller.Entities.Pessoas;
import com.gerenciamentopessoasprovatecnica.Controller.Repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class PessoaServiceImplements implements PessoaService{

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PessoaDto cadastro(PessoaDto dto) {

        Pessoas pessoas = modelMapper.map(dto, Pessoas.class);
        repository.save(pessoas);
        return modelMapper.map(pessoas,PessoaDto.class);
    }

    @Override
    public PessoaDto atualizacao(PessoaDto dto, UUID id) {


        verificaId(id);

        Pessoas pessoas = repository.getReferenceById(id);
        pessoas.setNome(dto.getNome());
        pessoas.setDataNascimento(dto.getDataNascimento());
        repository.save(pessoas);
        return modelMapper.map(pessoas,PessoaDto.class);
    }

    @Override
    public Page<PessoaDto> listar(Pageable pageable) {

     return repository.findAll(pageable).map(pessoas -> modelMapper.map(pessoas,PessoaDto.class));

    }

    @Override
    public PessoaDto buscarPorId(UUID id) {
        Pessoas pessoas = verificaId(id);
        return modelMapper.map(pessoas,PessoaDto.class);

    }
    private Pessoas verificaId(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
