package br.com.joaogosmani.ediaristas.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.joaogosmani.ediaristas.core.models.Servico;
import br.com.joaogosmani.ediaristas.web.dtos.ServicoForm;

@Mapper(componentModel = "spring")
public interface WebServicoMapper {
    
    WebServicoMapper INSTANCE = Mappers.getMapper(WebServicoMapper.class);
    
    Servico toModel(ServicoForm form);

    ServicoForm toForm(Servico model);

}
