package com.gerenciamentopessoasprovatecnica.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidacaoDto {

    private String campo;
    private String erro;
}
