package br.com.joaogosmani.ediaristas.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaogosmani.ediaristas.core.models.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    
}
