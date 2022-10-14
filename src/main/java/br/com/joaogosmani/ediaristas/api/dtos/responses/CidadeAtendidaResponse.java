package br.com.joaogosmani.ediaristas.api.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class CidadeAtendidaResponse {
    
    private Long id;

    private String cidade;

    private String estado;

    private String codigoIbge;

}
