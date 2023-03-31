package com.gerenciamentopessoasprovatecnica.Controller.service;

import com.gerenciamentopessoasprovatecnica.Controller.Dtos.CadastroEnderecoDto;
import com.gerenciamentopessoasprovatecnica.Controller.Dtos.EnderecoDto;
import com.gerenciamentopessoasprovatecnica.Controller.Dtos.PessoaDto;
import com.gerenciamentopessoasprovatecnica.Controller.Entities.Endereco;
import com.gerenciamentopessoasprovatecnica.Controller.Entities.Pessoas;
import com.gerenciamentopessoasprovatecnica.Controller.Repository.EnderecoRepository;
import com.gerenciamentopessoasprovatecnica.Controller.Repository.PessoaRepository;
import com.gerenciamentopessoasprovatecnica.Exceptions.PessoaNaoEncontrada;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImplements implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PessoaDto cadastro(PessoaDto dto) {

        Pessoas pessoas = modelMapper.map(dto, Pessoas.class);

        pessoas.setNome(dto.getNome());
        pessoas.setDataNascimento(dto.getDataNascimento());
        pessoas.getEnderecos().forEach(endereco -> endereco.setPessoas(pessoas));

        pessoaRepository.save(pessoas);
        return modelMapper.map(pessoas, PessoaDto.class);
    }

    @Override
    public PessoaDto atualizacao(PessoaDto dto, Long id) {

        verificaId(id);

        Pessoas pessoas = modelMapper.map(dto, Pessoas.class);

        pessoas.setId(id);
        pessoas.setNome(dto.getNome());
        pessoas.setDataNascimento(dto.getDataNascimento());

        pessoas.getEnderecos().forEach(endereco -> endereco.setPessoas(pessoas));


        pessoaRepository.save(pessoas);


        return modelMapper.map(pessoas, PessoaDto.class);
    }

    @Override
    public Page<PessoaDto> listar(Pageable pageable) {

        return pessoaRepository.findAll(pageable).map(pessoas -> modelMapper.map(pessoas, PessoaDto.class));

    }

    @Override
    public PessoaDto buscarPorId(Long id) {
        Pessoas pessoas = verificaId(id);
        return modelMapper.map(pessoas, PessoaDto.class);

    }

    private Pessoas verificaId(Long id) {
        return pessoaRepository.findById(id).orElseThrow(PessoaNaoEncontrada::new);
    }


    public CadastroEnderecoDto cadastrarEnderecoParaPessoa(CadastroEnderecoDto dto) {

        Endereco endereco = modelMapper.map(dto, Endereco.class);

        Pessoas pessoas = pessoaRepository.getReferenceById(dto.getPessoasId());

        endereco.setNumero(dto.getNumero());
        endereco.setCep(dto.getCep());
        endereco.setCidade(dto.getCidade());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setPessoas(pessoas);

        enderecoRepository.save(endereco);


        return modelMapper.map(endereco, CadastroEnderecoDto.class);

    }
}
