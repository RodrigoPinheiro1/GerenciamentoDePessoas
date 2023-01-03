package com.gerenciamentopessoasprovatecnica.Controller.service;

import com.gerenciamentopessoasprovatecnica.Controller.Dtos.PessoaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PessoaService {

    PessoaDto cadastro(PessoaDto dto);

    PessoaDto atualizacao(PessoaDto dto, UUID id);

    Page<PessoaDto> listar (Pageable pageable);

    PessoaDto buscarPorId(UUID id);

}
