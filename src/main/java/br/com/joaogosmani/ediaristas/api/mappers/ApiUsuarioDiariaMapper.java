package br.com.joaogosmani.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.joaogosmani.ediaristas.api.dtos.responses.UsuarioDiariaResponse;
import br.com.joaogosmani.ediaristas.core.models.Usuario;

@Mapper(componentModel = "spring")
public interface ApiUsuarioDiariaMapper {
    
    ApiUsuarioDiariaMapper INSTANCE = Mappers.getMapper(ApiUsuarioDiariaMapper.class);

    @Mapping(target = "tipoUsuario", source = "tipoUsuario.id")
    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    UsuarioDiariaResponse toResponse(Usuario model);

}
