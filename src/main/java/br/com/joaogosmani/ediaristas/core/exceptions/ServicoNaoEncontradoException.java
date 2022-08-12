package br.com.joaogosmani.ediaristas.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class ServicoNaoEncontradoException extends EntityNotFoundException {

    public ServicoNaoEncontradoException(String message) {
        super(message);
    }
    
}
