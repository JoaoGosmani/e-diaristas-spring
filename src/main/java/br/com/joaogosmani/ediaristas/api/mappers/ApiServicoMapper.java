package br.com.joaogosmani.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.joaogosmani.ediaristas.api.dtos.responses.ServicoResponse;
import br.com.joaogosmani.ediaristas.core.models.Servico;

@Mapper(componentModel = "spring")
public interface ApiServicoMapper {

    ApiServicoMapper INSTANCE = Mappers.getMapper(ApiServicoMapper.class);

    @Mapping(target = "icone", source = "icone.nome")
    ServicoResponse toResponse(Servico model);
    
}
