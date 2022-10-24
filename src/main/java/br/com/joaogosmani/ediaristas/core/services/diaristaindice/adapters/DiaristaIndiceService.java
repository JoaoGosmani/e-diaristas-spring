package br.com.joaogosmani.ediaristas.core.services.diaristaindice.adapters;

import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.models.Usuario;

public interface DiaristaIndiceService {
    
    public Usuario selecionarMelhorDiarista(Diaria diaria);

}
