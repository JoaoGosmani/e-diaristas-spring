package br.com.joaogosmani.ediaristas.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaogosmani.ediaristas.core.models.Diaria;
import br.com.joaogosmani.ediaristas.core.models.Usuario;

public interface DiariaRepository extends JpaRepository<Diaria, Long> {
    
    List<Diaria> findByCliente(Usuario cliente);

    List<Diaria> findByDiarista(Usuario diarista);

}
