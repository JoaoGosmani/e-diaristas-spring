package br.com.joaogosmani.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.core.models.Usuario;
import br.com.joaogosmani.ediaristas.core.repositories.UsuarioRepository;

@Service
public class WebUsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }

}
