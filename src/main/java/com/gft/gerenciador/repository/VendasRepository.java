package com.gft.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.gerenciador.domain.Vendas;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Long>{

}
