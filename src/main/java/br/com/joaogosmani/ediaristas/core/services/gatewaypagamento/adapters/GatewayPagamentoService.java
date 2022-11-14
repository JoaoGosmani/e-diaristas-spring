package br.com.joaogosmani.ediaristas.core.services.gatewaypagamento.adapters;

import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.models.Pagamento;

public interface GatewayPagamentoService {
    
    Pagamento pagar(Diaria diaria, String cardHash);
    
    Pagamento realizarEstornoTotal(Diaria diaria);

}
