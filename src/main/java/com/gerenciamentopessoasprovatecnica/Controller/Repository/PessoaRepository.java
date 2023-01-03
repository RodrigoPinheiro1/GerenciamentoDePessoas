package com.gerenciamentopessoasprovatecnica.Controller.Repository;

import com.gerenciamentopessoasprovatecnica.Controller.Entities.Endereco;
import com.gerenciamentopessoasprovatecnica.Controller.Entities.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoas, UUID> {



}
