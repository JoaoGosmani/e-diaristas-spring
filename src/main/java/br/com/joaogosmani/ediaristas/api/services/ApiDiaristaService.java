package br.com.joaogosmani.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.responses.DiaristaLocalidadesPagedResponse;
import br.com.joaogosmani.ediaristas.api.mappers.ApiDiaristaMapper;
import br.com.joaogosmani.ediaristas.core.models.Usuario;
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

    public DiaristaLocalidadesPagedResponse buscarDiaristasPorCep(String cep) {
        var codigoIbge = enderecoService.buscarEnderecoPorCep(cep).getIbge();

        var usuarioSort = Sort.sort(Usuario.class);
        var sort = usuarioSort.by(Usuario::getReputacao).descending();

        var numeroPagina = 0;
        var tamanhoPagina = 6;
        var pageable = PageRequest.of(numeroPagina, tamanhoPagina, sort);

        var resultado = repository.findByCidadesAtendidasCodigoIbge(codigoIbge, pageable);
        var diaristas = resultado.getContent()
            .stream()
            .map(mapper::toDiaristaLocalidadeResponse)
            .toList();

        return new DiaristaLocalidadesPagedResponse(diaristas, tamanhoPagina, resultado.getTotalElements());
    }

}
