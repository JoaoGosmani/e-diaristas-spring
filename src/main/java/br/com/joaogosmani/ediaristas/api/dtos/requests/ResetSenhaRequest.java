package br.com.joaogosmani.ediaristas.api.dtos.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetSenhaRequest {
    
    @Email
    @NotNull
    @NotEmpty
    private String email;

}
