package br.com.joaogosmani.ediaristas.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogosmani.ediaristas.api.dtos.requests.ResetSenhaConfirmacaoRequest;
import br.com.joaogosmani.ediaristas.api.dtos.requests.ResetSenhaRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.joaogosmani.ediaristas.api.services.ApiResetSenhaService;

@RestController
@RequestMapping("/api/recuperar-senha")
public class ResetSenhaRestController {
    
    @Autowired
    private ApiResetSenhaService service;

    @PostMapping
    public MensagemResponse solicitarResetDeSenha(@RequestBody @Valid ResetSenhaRequest request) {
        return service.solicitarResetDeSenha(request);
    }

    @PostMapping("/confirm")
    public MensagemResponse confirmarResetDeSenha(@RequestBody @Valid ResetSenhaConfirmacaoRequest request) {
        return service.confirmarResetDeSenha(request);
    }

}
