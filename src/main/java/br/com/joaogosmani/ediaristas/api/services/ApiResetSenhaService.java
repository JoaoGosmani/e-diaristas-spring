package br.com.joaogosmani.ediaristas.api.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.api.dtos.requests.ResetSenhaRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.joaogosmani.ediaristas.core.services.PasswordResetService;
import br.com.joaogosmani.ediaristas.core.services.email.adapters.EmailService;
import br.com.joaogosmani.ediaristas.core.services.email.dtos.EmailParams;

@Service
public class ApiResetSenhaService {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private EmailService emailService;

    @Value("${br.com.joaogosmani.ediaristas.frontend.host}")
    private String hostFrontend;

    public MensagemResponse solicitarResetDeSenha(ResetSenhaRequest request) {
        var passwordReset = passwordResetService.criarPasswordReset(request.getEmail());

        if (passwordReset != null) {
            var props = new HashMap<String, Object>();
            props.put("link", hostFrontend + "/recuperar-senha?token=" + passwordReset.getToken());
            var emailParams = EmailParams.builder()
                .destinatario(request.getEmail())
                .assunto("Solicitação de reset de senha")
                .template("emails/resetar-senha")
                .props(props)
                .build();
            emailService.enviarEmailComTemplateHtml(emailParams);
        }

        return new MensagemResponse("Verifique o seu e-mail para ter acesso ao link de reset de senha");
    }

}
