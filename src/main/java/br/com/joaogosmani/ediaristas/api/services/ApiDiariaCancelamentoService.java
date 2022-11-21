package br.com.joaogosmani.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.requests.DiariaCancelamentoRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.joaogosmani.ediaristas.core.enums.DiariaStatus;
import br.com.joaogosmani.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.repositories.DiariaRepository;

@Service
public class ApiDiariaCancelamentoService {
    
    @Autowired
    private DiariaRepository diariaRepository;

    public MensagemResponse cancelar(Long diariaId, DiariaCancelamentoRequest request) {
        var diaria = buscarDiariaPorId(diariaId);

        diaria.setStatus(DiariaStatus.CANCELADO);
        diaria.setMotivoCancelamento(request.getMotivoCancelamento());
        diariaRepository.save(diaria);

        return new MensagemResponse("A diária foi cancelada com sucesso!");
    }

    private Diaria buscarDiariaPorId(Long diariaId) {
        var mensagem = String.format("Diária com id %d não encontrada", diariaId);
        return diariaRepository.findById(diariaId)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

}
