package com.gft.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.gerenciador.domain.Casa;

@Repository
public interface CasaRepository extends JpaRepository<Casa, Long> {

}
