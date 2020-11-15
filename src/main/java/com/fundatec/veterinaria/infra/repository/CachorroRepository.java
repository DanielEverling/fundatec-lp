package com.fundatec.veterinaria.infra.repository;

import com.fundatec.veterinaria.domain.Cachorro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, Long> {
    Optional<Cachorro> findByNome(String nome);
}
