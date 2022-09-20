package br.com.joaogosmani.ediaristas.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaogosmani.ediaristas.api.dtos.requests.TokenRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.TokenResponse;
import br.com.joaogosmani.ediaristas.api.services.ApiAuthService;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    
    @Autowired
    private ApiAuthService service;

    @PostMapping("/token")
    public TokenResponse autenticar(@RequestBody @Valid TokenRequest tokenRequest) {
        return service.autenticar(tokenRequest);
    }

}
