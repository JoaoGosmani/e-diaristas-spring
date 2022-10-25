package br.com.joaogosmani.ediaristas.core.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.joaogosmani.ediaristas.core.enums.DiariaStatus;
import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.repositories.DiariaRepository;
import br.com.joaogosmani.ediaristas.core.services.diaristaindice.adapters.DiaristaIndiceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledTask {
    
    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private DiaristaIndiceService diaristaIndiceService;

    @Scheduled(cron = "0/10 * * * * ?")
    @Transactional(readOnly = false)
    public void selecionarDiaristaDaDiaria() {
        log.info("Iniciada task de seleção de diaristas para diárias aptas");

        var diariasAptasParaSelecao = diariaRepository.getAptasParaSelecaoDeDiarista();
        diariasAptasParaSelecao.stream().forEach(this::selecionarDiarista);

        log.info("Task de seleção de diarista finalizada");
    }

    private void selecionarDiarista(Diaria diaria) {
        log.info("Selecionando melhor diarista para diária de id " + diaria.getId().toString());
        var melhorDiarista = diaristaIndiceService.selecionarMelhorDiarista(diaria);
        diaria.setDiarista(melhorDiarista);
        diaria.setStatus(DiariaStatus.CONFIRMADO);
        diariaRepository.save(diaria);
        log.info("Selecionado o diarista de id " + melhorDiarista.getId().toString());
    }

}
