package br.com.joaogosmani.ediaristas.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogosmani.ediaristas.api.dtos.requests.DiariaCancelamentoRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.joaogosmani.ediaristas.api.services.ApiDiariaCancelamentoService;
import br.com.joaogosmani.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/diarias/{id}/cancelar")
public class DiariaCancelamentoRestController {
    
    @Autowired
    private ApiDiariaCancelamentoService service;

    @PatchMapping
    @EDiaristasPermissions.isClienteOrDiaristaDaDiaria
    public MensagemResponse cancelar(
        @PathVariable Long id, 
        @RequestBody @Valid DiariaCancelamentoRequest request
    ) {
        return service.cancelar(id, request);
    }     

}
