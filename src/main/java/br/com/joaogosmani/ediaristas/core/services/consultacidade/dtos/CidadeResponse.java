package br.com.joaogosmani.ediaristas.core.services.consultacidade.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadeResponse {
    
    private String ibge;

    private String cidade;

    private String estado;

}
