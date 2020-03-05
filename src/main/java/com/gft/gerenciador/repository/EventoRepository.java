package com.gft.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.gerenciador.domain.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
	
	Evento findByNome(String nome);
}
