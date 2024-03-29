package br.com.joaogosmani.ediaristas.core.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.core.exceptions.PasswordResetNotFound;
import br.com.joaogosmani.ediaristas.core.models.PasswordReset;
import br.com.joaogosmani.ediaristas.core.repositories.PasswordResetRepository;
import br.com.joaogosmani.ediaristas.core.repositories.UsuarioRepository;

@Service
public class PasswordResetService {
    
    @Autowired
    private PasswordResetRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PasswordReset criarPasswordReset(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            var passwordReset = PasswordReset.builder()
                .email(email)
                .token(UUID.randomUUID().toString())
                .build();
            return repository.save(passwordReset);
        }
        return null;
    }

    public void resetarSenha(String passwordResetToken, String novaSenha) {
        var passwordReset = buscarPasswordResetPortToken(passwordResetToken);
        var usuario = usuarioRepository.findByEmail(passwordReset.getEmail()).get();
        var novaSenhaHash = passwordEncoder.encode(novaSenha);
        usuario.setSenha(novaSenhaHash);
        usuarioRepository.save(usuario);
        repository.delete(passwordReset);
    }

    private PasswordReset buscarPasswordResetPortToken(String passwordResetToken) {
        return repository.findByToken(passwordResetToken)
            .orElseThrow(PasswordResetNotFound::new);
    }

}
