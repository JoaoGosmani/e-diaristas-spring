package br.com.joaogosmani.ediaristas.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class PasswordResetNotFound extends EntityNotFoundException {

    public PasswordResetNotFound() {
    }

    public PasswordResetNotFound(String message) {
        super(message);
    }
    
}
