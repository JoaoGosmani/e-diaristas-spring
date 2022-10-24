package br.com.joaogosmani.ediaristas.core.services.consultadistancia.adapters;

import br.com.joaogosmani.ediaristas.core.services.consultadistancia.dtos.DistanciaResponse;

public interface ConsultaDistanciaService {
    
    DistanciaResponse calcularDistanciaEntreDoisCeps(String origem, String destino);

}
