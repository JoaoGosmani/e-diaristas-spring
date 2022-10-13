package br.com.joaogosmani.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.requests.EnderecoDiaristaRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.EnderecoDiaristaResponse;
import br.com.joaogosmani.ediaristas.api.mappers.ApiEnderecoDiaristaMapper;
import br.com.joaogosmani.ediaristas.core.exceptions.EnderecoDiaristaNaoEncontradoException;
import br.com.joaogosmani.ediaristas.core.repositories.UsuarioRepository;
import br.com.joaogosmani.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiEnderecoDiaristaService {
    
    @Autowired
    private ApiEnderecoDiaristaMapper mapper;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    public EnderecoDiaristaResponse alterarEndereco(EnderecoDiaristaRequest request) {
        var usuarioLogado = securityUtils.getUsuarioLogado();

        var endereco = mapper.toModel(request);
        usuarioLogado.setEndereco(endereco);

        repository.save(usuarioLogado);

        return mapper.toResponse(usuarioLogado.getEndereco());
    }

    public EnderecoDiaristaResponse exibirEndereco() {
        var usuarioLogado = securityUtils.getUsuarioLogado();
        var endereco = usuarioLogado.getEndereco();

        if (endereco == null) {
            var mensagem = String.format("Endereço para usuário %s não encontrado", usuarioLogado.getEmail());
            throw new EnderecoDiaristaNaoEncontradoException(mensagem);
        }
        return mapper.toResponse(endereco);
    }

}
