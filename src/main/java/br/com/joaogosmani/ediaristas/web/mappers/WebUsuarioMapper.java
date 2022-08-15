package br.com.joaogosmani.ediaristas.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.joaogosmani.ediaristas.core.models.Usuario;
import br.com.joaogosmani.ediaristas.web.dtos.UsuarioCadastroForm;

@Mapper(componentModel = "spring")
public interface WebUsuarioMapper {
    
   WebUsuarioMapper INSTANCE = Mappers.getMapper(WebUsuarioMapper.class);
   
   Usuario toModel(UsuarioCadastroForm form);

}
