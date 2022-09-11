package br.com.joaogosmani.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.joaogosmani.ediaristas.api.dtos.responses.DiaristaLocalidadeResponse;
import br.com.joaogosmani.ediaristas.core.models.Usuario;

@Mapper(componentModel = "spring")
public interface ApiDiaristaMapper {
    
    ApiDiaristaMapper INSTANCE = Mappers.getMapper(ApiDiaristaMapper.class);

    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    DiaristaLocalidadeResponse toDiaristaLocalidadeResponse(Usuario model);

}
