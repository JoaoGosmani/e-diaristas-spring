package br.com.joaogosmani.ediaristas.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.responses.OportunidadeResponse;
import br.com.joaogosmani.ediaristas.api.mappers.ApiOportunidadeMapper;
import br.com.joaogosmani.ediaristas.core.repositories.DiariaRepository;
import br.com.joaogosmani.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiOportunidadeService {

    @Autowired
    private DiariaRepository repository;

    @Autowired 
    private ApiOportunidadeMapper mapper;

    @Autowired
    private SecurityUtils securityUtils;

    public List<OportunidadeResponse> buscarOportunidades() {
        var usuarioLogado = securityUtils.getUsuarioLogado();
        var cidades = usuarioLogado.getCidadesAtendidas()
            .stream()
            .map(cidade -> cidade.getCodigoIbge())
            .toList();
        return repository.findOportunidades(cidades, usuarioLogado)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

}
