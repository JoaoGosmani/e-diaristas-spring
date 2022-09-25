package br.com.joaogosmani.ediaristas.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaogosmani.ediaristas.core.models.TokenBlackList;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Long> {

    Boolean existsByToken(String token);

}
