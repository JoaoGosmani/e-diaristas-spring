package br.com.joaogosmani.ediaristas.core.exceptions;

import org.springframework.validation.FieldError;

public class ValidacaoException extends RuntimeException {
    
    private FieldError fieldError;

    public ValidacaoException(String message, FieldError fieldError) {
        super(message);
        this.fieldError = fieldError;
    }

    public FieldError GetFieldError() {
        return fieldError;
    }

}
