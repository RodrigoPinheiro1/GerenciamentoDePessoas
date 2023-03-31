package com.gerenciamentopessoasprovatecnica.Controller.Repository;

import com.gerenciamentopessoasprovatecnica.Controller.Entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long > {
}
