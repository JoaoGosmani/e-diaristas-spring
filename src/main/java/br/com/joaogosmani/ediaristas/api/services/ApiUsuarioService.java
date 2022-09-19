package br.com.joaogosmani.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.requests.UsuarioRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.UsuarioResponse;
import br.com.joaogosmani.ediaristas.api.mappers.ApiUsuarioMapper;
import br.com.joaogosmani.ediaristas.core.repositories.UsuarioRepository;

@Service
public class ApiUsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ApiUsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponse cadastrarUsuario(UsuarioRequest request) {
        var usuarioParaCadastrar = mapper.toModel(request);

        var senhaEncriptada = passwordEncoder.encode(usuarioParaCadastrar.getSenha());
        usuarioParaCadastrar.setSenha(senhaEncriptada);

        var usuarioCadastrado = repository.save(usuarioParaCadastrar);

        return mapper.toResponse(usuarioCadastrado);
    }

}
