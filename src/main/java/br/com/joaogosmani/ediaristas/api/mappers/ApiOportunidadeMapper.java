package br.com.joaogosmani.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.joaogosmani.ediaristas.api.dtos.responses.OportunidadeResponse;
import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.models.Servico;
import br.com.joaogosmani.ediaristas.core.repositories.ServicoRepository;

@Mapper(componentModel = "spring", uses = ApiUsuarioDiariaMapper.class)
public abstract class ApiOportunidadeMapper {
    
    @Autowired
    protected ServicoRepository servicoRepository;

    public static final ApiOportunidadeMapper INSTANCE = Mappers.getMapper(ApiOportunidadeMapper.class);

    @Mapping(target = "status", source = "status.id")
    @Mapping(target = "nomeServico", source = "servico.nome")
    @Mapping(target = "servico", source = "servico.id")
    public abstract OportunidadeResponse toResponse(Diaria model);

    protected Servico longToServico(Long servicoId) {
        return servicoRepository.findById(servicoId)
            .orElseThrow(IllegalArgumentException::new);
    }

}
