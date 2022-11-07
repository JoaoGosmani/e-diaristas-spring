package br.com.joaogosmani.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.requests.PagamentoRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.joaogosmani.ediaristas.core.enums.DiariaStatus;
import br.com.joaogosmani.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.repositories.DiariaRepository;
import br.com.joaogosmani.ediaristas.core.services.gatewaypagamento.adapters.GatewayPagamentoService;
import br.com.joaogosmani.ediaristas.core.validators.PagamentoValidator;

@Service
public class ApiDiariaPagamentoService {
   
    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private PagamentoValidator validator;

    @Autowired
    private GatewayPagamentoService gatewayPagamentoService;

    public MensagemResponse pagar(PagamentoRequest request, Long id) {
        var diaria = buscarDiariaPorId(id);

        validator.validar(diaria);

        var pagamento = gatewayPagamentoService.pagar(diaria, request.getCardHash());
        if (pagamento.isAceito()) {
            diaria.setStatus(DiariaStatus.PAGO);
            diariaRepository.save(diaria);
            return new MensagemResponse("Diária paga com sucesso!");
        }
        return new MensagemResponse("Pagamento recusado");
    }

    private Diaria buscarDiariaPorId(Long id) {
        var mensagem = String.format("Diária com id %d não encontrada", id);
        return diariaRepository.findById(id)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem)); 
    }

}
