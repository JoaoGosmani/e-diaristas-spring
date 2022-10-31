package br.com.joaogosmani.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.joaogosmani.ediaristas.core.models.Avaliacao;
import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.models.Usuario;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
    boolean existsByDiariaAndAvaliador(Diaria diaria, Usuario avaliador);

    @Query(
        """
        SELECT
            AVG(a.nota)
        FROM 
            Avaliacao a
        WHERE
            a.avaliado = :usuario 
        """
    )
    Double getAvaliacaoMedia(Usuario usuario);

    @Query(
        """
        SELECT
            COUNT(*) = 2
        FROM
            Avaliacao a
        WHERE
            a.diaria = :diaria
        """
    )
    boolean isClienteAndDiaristaAvaliaramDiaria(Diaria diaria);

}
