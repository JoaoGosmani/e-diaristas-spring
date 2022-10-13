package br.com.joaogosmani.ediaristas.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class EnderecoDiaristaNaoEncontradoException extends EntityNotFoundException {

    public EnderecoDiaristaNaoEncontradoException(String message) {
        super(message);
    } 

}
