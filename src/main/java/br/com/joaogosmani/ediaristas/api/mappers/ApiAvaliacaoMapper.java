package br.com.joaogosmani.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.joaogosmani.ediaristas.api.dtos.requests.AvaliacaoRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.AvaliacaoResponse;
import br.com.joaogosmani.ediaristas.core.models.Avaliacao;

@Mapper(componentModel = "spring")
public interface ApiAvaliacaoMapper {
    
    ApiAvaliacaoMapper INSTANCE = Mappers.getMapper(ApiAvaliacaoMapper.class);

    Avaliacao toModel(AvaliacaoRequest request);

    @Mapping(target = "nomeAvaliador", source = "avaliador.nomeCompleto")
    @Mapping(target = "fotoAvaliador", source = "avaliador.fotoUsuario.url")
    AvaliacaoResponse toResponse(Avaliacao model);

}
