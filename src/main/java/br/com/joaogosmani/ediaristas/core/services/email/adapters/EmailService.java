package br.com.joaogosmani.ediaristas.core.services.email.adapters;

import br.com.joaogosmani.ediaristas.core.services.email.dtos.EmailParams;

public interface EmailService {
    
    void  enviarEmailComTemplateHtml(EmailParams params);

}
