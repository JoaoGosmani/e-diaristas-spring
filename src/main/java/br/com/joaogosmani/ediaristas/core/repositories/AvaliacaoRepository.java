package br.com.joaogosmani.ediaristas.core.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    boolean existsByAvaliadoraAndDiariaId(Usuario avaliador, Long diariaId);

    Page<Avaliacao> findByAvaliado(Usuario avaliado, Pageable pageable);

    default List<Avaliacao> getUltimasAvaliacoes(Usuario avaliado) {
        var avaliacaoSort = Sort.sort(Avaliacao.class);
        var sort = avaliacaoSort.by(Avaliacao::getCreatedAt).descending();
        var pageable = PageRequest.of(0, 2, sort); 
        return findByAvaliado(avaliado, pageable).getContent();
    }

}
