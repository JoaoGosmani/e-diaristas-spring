package br.com.joaogosmani.ediaristas.core.services.consultacidade.adapters;

import br.com.joaogosmani.ediaristas.core.services.consultacidade.dtos.CidadeResponse;
import br.com.joaogosmani.ediaristas.core.services.consultacidade.exceptions.ConsultaCidadeException;

public interface ConsultaCidadeService {
    
    CidadeResponse buscarCidadePorCodigoIbge(String codigoIbge) throws ConsultaCidadeException;

}
