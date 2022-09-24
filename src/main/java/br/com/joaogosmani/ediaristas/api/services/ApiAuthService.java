package br.com.joaogosmani.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.requests.RefreshRequest;
import br.com.joaogosmani.ediaristas.api.dtos.requests.TokenRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.TokenResponse;
import br.com.joaogosmani.ediaristas.core.services.token.adapters.TokenService;

@Service
public class ApiAuthService {
    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenResponse autenticar(TokenRequest tokenRequest) {
        var email = tokenRequest.getEmail();
        var senha = tokenRequest.getSenha();

        var autenticacao = new UsernamePasswordAuthenticationToken(email, senha);
        authenticationManager.authenticate(autenticacao);

        var access = tokenService.gerarAccessToken(email);
        var refresh = tokenService.gerarRefreshToken(email);

        return new TokenResponse(access, refresh);
    }

    public TokenResponse reautenticar(RefreshRequest refreshRequest) {
        var email = tokenService.getSubjectDoRefreshToken(refreshRequest.getRefresh());

        userDetailsService.loadUserByUsername(email);

        var access = tokenService.gerarAccessToken(email);
        var refresh = tokenService.gerarRefreshToken(email);

        return new TokenResponse(access, refresh);
    }

}
