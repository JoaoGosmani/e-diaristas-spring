package br.com.joaogosmani.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaogosmani.ediaristas.core.enums.TipoUsuario;
import br.com.joaogosmani.ediaristas.core.models.Usuario;
import br.com.joaogosmani.ediaristas.core.repositories.UsuarioRepository;
import br.com.joaogosmani.ediaristas.web.dtos.UsuarioCadastroForm;
import br.com.joaogosmani.ediaristas.web.mappers.WebUsuarioMapper;

@Service
public class WebUsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private WebUsuarioMapper mapper;

    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }

    public Usuario cadastrar(UsuarioCadastroForm form) {
        var model = mapper.toModel(form);
        
        model.setTipoUsuario(TipoUsuario.ADMIN);

        return repository.save(model);
    }

}
