package br.com.joaogosmani.ediaristas.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogosmani.ediaristas.api.dtos.requests.EnderecoDiaristaRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.EnderecoDiaristaResponse;
import br.com.joaogosmani.ediaristas.api.services.ApiEnderecoDiaristaService;
import br.com.joaogosmani.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/usuarios/endereco")
public class EnderecoDiaristaRestController {
    
    @Autowired
    private ApiEnderecoDiaristaService service;

    @EDiaristasPermissions.isDiarista
    @PutMapping
    public EnderecoDiaristaResponse alterarEndereco(@RequestBody @Valid EnderecoDiaristaRequest request) {
        return service.alterarEndereco(request);
    }

    @EDiaristasPermissions.isDiarista
    @GetMapping
    public EnderecoDiaristaResponse exibirEndereco() {
        return service.exibirEndereco();
    }

}
