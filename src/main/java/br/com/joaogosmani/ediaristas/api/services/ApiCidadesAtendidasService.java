package br.com.joaogosmani.ediaristas.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.responses.CidadeAtendidaResponse;
import br.com.joaogosmani.ediaristas.api.mappers.ApiCidadeAtendidaMapper;
import br.com.joaogosmani.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiCidadesAtendidasService {
    
    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private ApiCidadeAtendidaMapper mapper;
    
    public List<CidadeAtendidaResponse> listarCidadesAtendidas() {
        return securityUtils.getUsuarioLogado()
            .getCidadesAtendidas()
            .stream()
            .map(mapper::toResponse)
            .toList();
    }
    
}
