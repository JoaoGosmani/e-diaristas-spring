package br.com.joaogosmani.ediaristas.api.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.requests.DiariaCancelamentoRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.joaogosmani.ediaristas.core.enums.DiariaStatus;
import br.com.joaogosmani.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.repositories.DiariaRepository;
import br.com.joaogosmani.ediaristas.core.services.gatewaypagamento.adapters.GatewayPagamentoService;
import br.com.joaogosmani.ediaristas.core.validators.DiariaCancelamentoValidator;

@Service
public class ApiDiariaCancelamentoService {
    
    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private DiariaCancelamentoValidator validator;

    @Autowired
    private GatewayPagamentoService gatewayPagamentoService;

    public MensagemResponse cancelar(Long diariaId, DiariaCancelamentoRequest request) {
        var diaria = buscarDiariaPorId(diariaId);
        validator.validar(diaria);

        diaria.setStatus(DiariaStatus.CANCELADO);
        diaria.setMotivoCancelamento(request.getMotivoCancelamento());
        diariaRepository.save(diaria);

        if (hasPenalizacao(diaria)) {
            aplicarPenalizacao(diaria);
        } else {
            gatewayPagamentoService.realizarEstornoTotal(diaria);
        }

        return new MensagemResponse("A diária foi cancelada com sucesso!");
    }

    private void aplicarPenalizacao(Diaria diaria) {
    }

    private Diaria buscarDiariaPorId(Long diariaId) {
        var mensagem = String.format("Diária com id %d não encontrada", diariaId);
        return diariaRepository.findById(diariaId)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

    private boolean hasPenalizacao(Diaria diaria) {
        var hoje = LocalDateTime.now();
        return ChronoUnit.HOURS.between(hoje, diaria.getDataAtendimento()) < 24;
    }

}
