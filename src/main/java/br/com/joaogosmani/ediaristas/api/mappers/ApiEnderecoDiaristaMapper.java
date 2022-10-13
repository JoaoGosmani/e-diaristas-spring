package br.com.joaogosmani.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.joaogosmani.ediaristas.api.dtos.requests.EnderecoDiaristaRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.EnderecoDiaristaResponse;
import br.com.joaogosmani.ediaristas.core.models.EnderecoDiarista;

@Mapper(componentModel = "spring")
public interface ApiEnderecoDiaristaMapper {
    
    ApiEnderecoDiaristaMapper INSTANCE = Mappers.getMapper(ApiEnderecoDiaristaMapper.class);

    EnderecoDiarista toModel(EnderecoDiaristaRequest request);

    EnderecoDiaristaResponse toResponse(EnderecoDiarista model);

}
