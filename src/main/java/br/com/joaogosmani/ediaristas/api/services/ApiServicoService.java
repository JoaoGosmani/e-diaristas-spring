package br.com.joaogosmani.ediaristas.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.responses.ServicoResponse;
import br.com.joaogosmani.ediaristas.api.mappers.ApiServicoMapper;
import br.com.joaogosmani.ediaristas.core.models.Servico;
import br.com.joaogosmani.ediaristas.core.repositories.ServicoRepository;

@Service
public class ApiServicoService {
    
    @Autowired
    private ServicoRepository repository;

    @Autowired
    private ApiServicoMapper mapper;

    public List<ServicoResponse> buscarTodos() {
        var servicoSort = Sort.sort(Servico.class);
        var ordenacao = servicoSort.by(Servico::getPosicao).ascending();

        return repository.findAll(ordenacao)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

}
