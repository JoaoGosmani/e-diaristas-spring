package br.com.joaogosmani.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.joaogosmani.ediaristas.api.dtos.responses.ClienteResponse;
import br.com.joaogosmani.ediaristas.core.models.Usuario;

@Mapper(componentModel = "spring")
public interface ApiClienteMapper {
    
    ApiClienteMapper INSTANCE = Mappers.getMapper(ApiClienteMapper.class);

    @Mapping(target = "tipoUsuario", source = "tipoUsuario.id")
    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    ClienteResponse toResponse(Usuario model);

}
