package br.com.joaogosmani.ediaristas.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.responses.DiaristaLocalidadeResponse;
import br.com.joaogosmani.ediaristas.api.mappers.ApiDiaristaMapper;
import br.com.joaogosmani.ediaristas.core.repositories.UsuarioRepository;
import br.com.joaogosmani.ediaristas.core.services.consultaendereco.adapters.EnderecoService;

@Service
public class ApiDiaristaService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ApiDiaristaMapper mapper;

    @Autowired
    private EnderecoService enderecoService;

    public List<DiaristaLocalidadeResponse> buscarDiaristasPorCep(String cep) {
        var codigoIbge = enderecoService.buscarEnderecoPorCep(cep).getIbge();

        return repository.findByCidadesAtendidasCodigoIbge(codigoIbge)
            .stream()
            .map(mapper::toDiaristaLocalidadeResponse)
            .toList();
    }

}
