package br.com.joaogosmani.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.core.enums.DiariaStatus;
import br.com.joaogosmani.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.repositories.DiariaRepository;
import br.com.joaogosmani.ediaristas.core.validators.TransferenciaValidator;

@Service
public class WebDiariaService {
    
    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private TransferenciaValidator validator;

    public List<Diaria> buscarDiarias(String cliente, List<DiariaStatus> status) {
        var diariaSort = Sort.sort(Diaria.class);
        var sort = diariaSort.by(Diaria::getDataAtendimento).descending();
        return diariaRepository.findComFiltro(cliente, status, sort);
    }

    public void pagar(Long id) {
        var diaria = buscarDiariaPorId(id);
        validator.validar(diaria);
        diaria.setStatus(DiariaStatus.TRANSFERIDO);
        diariaRepository.save(diaria);
    }

    private Diaria buscarDiariaPorId(Long id) {
        var mensagem = String.format("Diária com id %d não encontrada!", id);
        return diariaRepository.findById(id)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

}
