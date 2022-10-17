package br.com.joaogosmani.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.joaogosmani.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.repositories.DiariaRepository;
import br.com.joaogosmani.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiCandidaturaService {
    
    @Autowired
    private DiariaRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    public MensagemResponse candidatar(Long id) {
        var diaria = buscarDiariaPorId(id);
        var usuarioLogado = securityUtils.getUsuarioLogado();
        diaria.getCandidatos().add(usuarioLogado);
        repository.save(diaria);
        return new MensagemResponse("Candidatura realizada com sucesso!");
    }

    private Diaria buscarDiariaPorId(Long id) {
        var mensagem = String.format("Diária com id %d não encontrada", id);
        return repository.findById(id)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

}
