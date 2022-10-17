package br.com.joaogosmani.ediaristas.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.models.Usuario;

public interface DiariaRepository extends JpaRepository<Diaria, Long> {
    
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

}
