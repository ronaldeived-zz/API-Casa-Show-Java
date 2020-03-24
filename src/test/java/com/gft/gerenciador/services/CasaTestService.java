package com.gft.gerenciador.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.gerenciador.domain.Casa;
import com.gft.gerenciador.repository.CasaRepository;
import com.gft.gerenciador.service.CasaService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CasaTestService {
	
	@InjectMocks
	private CasaService service;
	
	private Casa casa = new Casa("Casa Euphoria", "Bandeirantes");
	
	@Autowired
	private TestEntityManager entityManager;
	 
	@Autowired
	private CasaRepository casaRepository;
	
	@Test
	public void deveSalvarCasaDeShow() {
		entityManager.persist(casa);
		entityManager.flush();
		
		Casa procuraCasa = casaRepository.findByNome(casa.getNome());
		
		Assert.assertEquals(procuraCasa.getNome(), casa.getNome());
	}
	
	@Test
	public void deveListarCasas() {

		
	}
}
