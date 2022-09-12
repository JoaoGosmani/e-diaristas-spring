package br.com.joaogosmani.ediaristas.core.services.consultaendereco.adapters;

import br.com.joaogosmani.ediaristas.core.services.consultaendereco.dtos.EnderecoResponse;
import br.com.joaogosmani.ediaristas.core.services.consultaendereco.exceptions.EnderecoServiceException;

public interface EnderecoService {
    
    EnderecoResponse buscarEnderecoPorCep(String cep) throws EnderecoServiceException;

}
