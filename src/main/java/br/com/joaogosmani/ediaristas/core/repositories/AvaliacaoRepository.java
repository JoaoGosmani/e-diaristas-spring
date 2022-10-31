package br.com.joaogosmani.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaogosmani.ediaristas.core.models.Avaliacao;
import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.models.Usuario;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
    boolean existsByDiariaAndAvaliador(Diaria diaria, Usuario avaliador);

}
