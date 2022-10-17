package br.com.joaogosmani.ediaristas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogosmani.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.joaogosmani.ediaristas.api.services.ApiCandidaturaService;
import br.com.joaogosmani.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/diarias/{id}/candidatar")
public class CandidaturaRestController {
    
    @Autowired
    private ApiCandidaturaService service;

    @PostMapping
    @EDiaristasPermissions.isDiarista
    public MensagemResponse candidatar(@PathVariable Long id) {
        return service.candidatar(id);
    }

}
