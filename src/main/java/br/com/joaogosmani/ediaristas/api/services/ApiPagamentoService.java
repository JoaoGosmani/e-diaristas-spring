package br.com.joaogosmani.ediaristas.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.responses.PagamentoResponse;
import br.com.joaogosmani.ediaristas.api.mappers.ApiPagamentoMapper;
import br.com.joaogosmani.ediaristas.core.enums.DiariaStatus;
import br.com.joaogosmani.ediaristas.core.repositories.PagamentoRepository;
import br.com.joaogosmani.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiPagamentoService {
    
    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ApiPagamentoMapper mapper;

    @Autowired
    private SecurityUtils securityUtils;

    public List<PagamentoResponse> listarPagamentos() {
        var usuarioLogado = securityUtils.getUsuarioLogado();
        var status = List.of(
            DiariaStatus.CONCLUIDO,
            DiariaStatus.AVALIADO,
            DiariaStatus.TRANSFERIDO
        );

        return repository.findByDiariaDiaristaAndDiariaStatusIn(usuarioLogado, status)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

}
