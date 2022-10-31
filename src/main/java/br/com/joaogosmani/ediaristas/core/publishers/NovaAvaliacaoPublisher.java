package br.com.joaogosmani.ediaristas.core.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import br.com.joaogosmani.ediaristas.core.events.NovaAvaliacaoEvent;
import br.com.joaogosmani.ediaristas.core.models.Avaliacao;

@Component
public class NovaAvaliacaoPublisher {
    
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(Avaliacao avaliacao) {
        var event = new NovaAvaliacaoEvent(this, avaliacao);
        applicationEventPublisher.publishEvent(event);
    }

}
