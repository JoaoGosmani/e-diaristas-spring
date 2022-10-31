package br.com.joaogosmani.ediaristas.core.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.joaogosmani.ediaristas.core.enums.DiariaStatus;
import br.com.joaogosmani.ediaristas.core.events.NovaAvaliacaoEvent;
import br.com.joaogosmani.ediaristas.core.models.Avaliacao;
import br.com.joaogosmani.ediaristas.core.repositories.AvaliacaoRepository;
import br.com.joaogosmani.ediaristas.core.repositories.DiariaRepository;
import br.com.joaogosmani.ediaristas.core.repositories.UsuarioRepository;

@Component
public class NovaAvaliacaoListener {
    
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DiariaRepository diariaRepository;

    @EventListener
    public void handleNovaAvaliacaoEvent(NovaAvaliacaoEvent event) {
        var avaliacao = event.getAvaliacao();
        atualizarReputacaoAvaliado(avaliacao);
        atualizarStatusDiariaAvaliada(avaliacao);
    }

    private void atualizarStatusDiariaAvaliada(Avaliacao avaliacao) {
        var diaria = avaliacao.getDiaria();
        if (avaliacaoRepository.isClienteAndDiaristaAvaliaramDiaria(diaria)) {
            diaria.setStatus(DiariaStatus.AVALIADO);
            diariaRepository.save(diaria);
        }
    }

    private void atualizarReputacaoAvaliado(Avaliacao avaliacao) {
        var avaliado = avaliacao.getAvaliado();
        var notaMedia = avaliacaoRepository.getAvaliacaoMedia(avaliado);
        avaliado.setReputacao(notaMedia);
        usuarioRepository.save(avaliado);
    }

}
