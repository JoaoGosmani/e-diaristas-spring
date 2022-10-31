package br.com.joaogosmani.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaogosmani.ediaristas.core.models.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
}
