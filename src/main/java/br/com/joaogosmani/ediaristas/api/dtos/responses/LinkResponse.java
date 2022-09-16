package br.com.joaogosmani.ediaristas.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkResponse {
    
    private String type;

    private String rel;

    private String uri;

}
