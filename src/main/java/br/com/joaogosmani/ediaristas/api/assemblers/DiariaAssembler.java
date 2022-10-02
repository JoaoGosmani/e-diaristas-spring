package br.com.joaogosmani.ediaristas.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.joaogosmani.ediaristas.api.controllers.DiariaPagamentoRestController;
import br.com.joaogosmani.ediaristas.api.dtos.responses.DiariaResponse;
import br.com.joaogosmani.ediaristas.core.utils.SecurityUtils;

@Component
public class DiariaAssembler implements Assembler<DiariaResponse> {

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public void adicionarLinks(DiariaResponse resource) {
        if (securityUtils.isCliente() && resource.isSemPagamento()) {
            var id = resource.getId();
            var pagarDiariaLink = linkTo(methodOn(DiariaPagamentoRestController.class).pagar(null, id))
                .withRel("pagar_diaria")
                .withType("POST");

            resource.adicionarLinks(pagarDiariaLink);
        }  
    }

    @Override
    public void adicionarLinks(List<DiariaResponse> collectionResource) {}
    
}
