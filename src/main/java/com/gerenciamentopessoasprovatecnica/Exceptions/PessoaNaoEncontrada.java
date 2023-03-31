package com.gerenciamentopessoasprovatecnica.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class PessoaNaoEncontrada extends RuntimeException {


    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PessoaNaoEncontrada.class)
    public ResponseEntity<MessageGlobalException> pessoaNaoEncontrada () {

        MessageGlobalException exception = new MessageGlobalException(LocalDateTime.now()
                ,HttpStatus.NOT_FOUND.value(),"usuario nao encontrado");


        return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
    }
}
