package br.com.joaogosmani.ediaristas.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashMessage {
    
    private String classeCss;

    private String mensagem;

}
