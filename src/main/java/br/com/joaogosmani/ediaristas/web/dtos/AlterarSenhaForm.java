package br.com.joaogosmani.ediaristas.web.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.joaogosmani.ediaristas.web.interfaces.IConfirmacaoSenha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlterarSenhaForm implements IConfirmacaoSenha {
    
    @NotNull
    @NotEmpty
    private String senhaAntiga;

    @NotNull
    @NotEmpty
    private String senha;

    @NotNull
    @NotEmpty
    private String confirmacaoSenha;

}
