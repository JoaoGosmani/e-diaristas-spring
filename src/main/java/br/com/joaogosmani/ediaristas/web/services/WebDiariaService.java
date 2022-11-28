package br.com.joaogosmani.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.repositories.DiariaRepository;

@Service
public class WebDiariaService {
    
    @Autowired
    private DiariaRepository diariaRepository;

    public List<Diaria> buscarDiarias() {
        return diariaRepository.findAll();
    }

}
