package br.com.joaogosmani.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.joaogosmani.ediaristas.api.dtos.responses.CidadeAtendidaResponse;
import br.com.joaogosmani.ediaristas.core.models.CidadeAtendida;

@Mapper(componentModel = "spring")
public interface ApiCidadeAtendidaMapper {
    
    ApiCidadeAtendidaMapper INSTANCE = Mappers.getMapper(ApiCidadeAtendidaMapper.class);

    CidadeAtendidaResponse toResponse(CidadeAtendida model);

}
