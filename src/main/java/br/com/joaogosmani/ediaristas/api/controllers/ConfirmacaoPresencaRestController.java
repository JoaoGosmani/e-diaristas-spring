package br.com.joaogosmani.ediaristas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogosmani.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.joaogosmani.ediaristas.api.services.ApiConfirmacaoPresencaService;
import br.com.joaogosmani.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/diarias/{id}/confirmar-presenca")
public class ConfirmacaoPresencaRestController {
    
    @Autowired
    private ApiConfirmacaoPresencaService service;

    @PatchMapping
    @EDiaristasPermissions.isClienteDaDiaria
    public MensagemResponse confirmarPresenca(@PathVariable Long id) {
        return service.confirmarPresenca(id);
    }

}
