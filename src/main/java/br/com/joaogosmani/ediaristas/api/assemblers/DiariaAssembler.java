package br.com.joaogosmani.ediaristas.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.joaogosmani.ediaristas.api.controllers.ConfirmacaoPresencaRestController;
import br.com.joaogosmani.ediaristas.api.controllers.DiariaPagamentoRestController;
import br.com.joaogosmani.ediaristas.api.controllers.DiariaRestController;
import br.com.joaogosmani.ediaristas.api.dtos.responses.DiariaResponse;
import br.com.joaogosmani.ediaristas.core.utils.SecurityUtils;

@Component
public class DiariaAssembler implements Assembler<DiariaResponse> {

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public void adicionarLinks(DiariaResponse resource) {
        var id = resource.getId();
        if (securityUtils.isCliente() && resource.isSemPagamento()) {
            var pagarDiariaLink = linkTo(methodOn(DiariaPagamentoRestController.class).pagar(null, id))
                .withRel("pagar_diaria")
                .withType("POST");

            resource.adicionarLinks(pagarDiariaLink);
        }  

        if (isAptaParaConfirmacaoDePresenca(resource)) {
            var confirmarPresencaLink =  linkTo(methodOn(ConfirmacaoPresencaRestController.class).confirmarPresenca(id))
                .withRel("confirmar_diarista")
                .withType("PATCH");

            resource.adicionarLinks(confirmarPresencaLink);
        }

        var selfLink = linkTo(methodOn(DiariaRestController.class).buscarPorId(id))
            .withSelfRel()
            .withType("GET");

            resource.adicionarLinks(selfLink);
    }

    @Override
    public void adicionarLinks(List<DiariaResponse> collectionResource) {
        collectionResource.stream()
            .forEach(this::adicionarLinks);
    }

    private boolean isAptaParaConfirmacaoDePresenca(DiariaResponse resource) {
        return resource.isConfirmado() 
            && isDataAtendimentoNoPassado(resource)
            && resource.getDiarista() != null;
    }

    private boolean isDataAtendimentoNoPassado(DiariaResponse resource) {
        return resource.getDataAtendimento().isBefore(LocalDateTime.now());
    }
    
}
