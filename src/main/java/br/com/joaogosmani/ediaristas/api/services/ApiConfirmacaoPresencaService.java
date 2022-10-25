package br.com.joaogosmani.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.joaogosmani.ediaristas.core.enums.DiariaStatus;
import br.com.joaogosmani.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.repositories.DiariaRepository;
import br.com.joaogosmani.ediaristas.core.validators.ConfirmacaoPresencaValidator;

@Service
public class ApiConfirmacaoPresencaService {
    
    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private ConfirmacaoPresencaValidator validator;

    public MensagemResponse confirmarPresenca(Long id) {
        var diaria = buscarDiariaPorId(id);
        validator.validar(diaria);
        diaria.setStatus(DiariaStatus.CONCLUIDO);
        diariaRepository.save(diaria);
        return new MensagemResponse("Presença confirmada com sucesso!");
    }

    private Diaria buscarDiariaPorId(Long id) {
        var mensagem = String.format("Diária de id %d não encontrada", id);
        return diariaRepository.findById(id)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

}
