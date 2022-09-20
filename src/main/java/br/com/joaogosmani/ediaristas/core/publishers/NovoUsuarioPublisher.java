package br.com.joaogosmani.ediaristas.core.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import br.com.joaogosmani.ediaristas.core.events.NovoUsuarioEvent;
import br.com.joaogosmani.ediaristas.core.models.Usuario;

@Component
public class NovoUsuarioPublisher {
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void publish(Usuario usuario) {
        var event = new NovoUsuarioEvent(this, usuario);
        eventPublisher.publishEvent(event);
    }

}
