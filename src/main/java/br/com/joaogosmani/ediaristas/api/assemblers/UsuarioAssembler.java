package br.com.joaogosmani.ediaristas.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.joaogosmani.ediaristas.api.controllers.DiariaRestController;
import br.com.joaogosmani.ediaristas.api.dtos.responses.UsuarioResponse;

@Component
public class UsuarioAssembler implements Assembler<UsuarioResponse> {

    @Override
    public void adicionarLinks(UsuarioResponse resource) {
        if (resource.isCliente()) {
            var cadastrarDiariaLink = linkTo(methodOn(DiariaRestController.class).cadastrar(null))
                .withRel("cadastrar_diaria")
                .withType("POST");

                resource.adicionarLinks(cadastrarDiariaLink);
        }
    }

    @Override
    public void adicionarLinks(List<UsuarioResponse> collectionResource) {
        
    }
    
}
