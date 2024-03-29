package com.gerenciamentopessoasprovatecnica.Exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {



    @Autowired
    private MessageSource messageSource;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MessageGlobalException handle (MethodArgumentNotValidException exception){

        List <ValidacaoDto> validacaoDtos = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors(); // getBindingResult faz uma vinculação com o erro
        // get field erro pega qualquer erro que estiver no primeiro

        MessageGlobalException  customGlobalException = new MessageGlobalException(
                LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(), "erro de digitação", validacaoDtos);

        fieldErrors.forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            //localeContext serve para ver a posicção atual da list
            ValidacaoDto erro = new ValidacaoDto(fieldError.getField(),message);
            validacaoDtos.add(erro);
        });

        return customGlobalException;
    }
}
