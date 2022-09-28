package br.com.joaogosmani.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaogosmani.ediaristas.core.models.Diaria;

public interface DiariaRepository extends JpaRepository<Diaria, Long> {
    
}
