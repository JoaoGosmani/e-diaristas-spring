package br.com.joaogosmani.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaogosmani.ediaristas.core.models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    
}
