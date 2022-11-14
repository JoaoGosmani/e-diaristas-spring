package br.com.joaogosmani.ediaristas.core.repositories;

import static br.com.joaogosmani.ediaristas.core.specifications.DiariaSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.models.Usuario;

public interface DiariaRepository extends 
    JpaRepository<Diaria, Long>, 
    JpaSpecificationExecutor<Diaria> 
{
    
    List<Diaria> findByCliente(Usuario cliente);

    List<Diaria> findByDiarista(Usuario diarista);

    @Query(
        """
        SELECT
            d
        FROM
            Diaria d
        WHERE
            d.status = br.com.joaogosmani.ediaristas.core.enums.DiariaStatus.PAGO
        AND
            d.diarista IS NULL
        AND
            d.codigoIbge IN :cidades
        AND
            :candidato NOT MEMBER OF d.candidatos
        AND
            SIZE(d.candidatos) < 3
        """
    )
    List<Diaria> findOportunidades(List<String> cidades, Usuario candidato);

    default List<Diaria> getAptasParaSelecaoDeDiarista() {
        return this.findAll(
            where(
                isPago()
                .and(semDiarista())
                .and(comNumeroCandidatosIgualA(3))
            ).or(
                isPago()
                .and(semDiarista())
                .and(comMaisDe24HorasDesdeCriacao())
                .and(comNumeroCandidatosMenorQue(3))
                .and(comNumeroCandidatosMaiorOuIgualA(1))
            )
        );
    }

    default List<Diaria> getAptasParaCancelamento() {
        return this.findAll(
            where(
                isPago()
                .and(comMenos24HorasParaAtendimento())
                .and(semCandidatos())
            ).or(
                isSemPagamento()
                .and(comMaisDe24HorasDesdeCriacao())
                .and(semCandidatos())
            )
        );
    }

}
