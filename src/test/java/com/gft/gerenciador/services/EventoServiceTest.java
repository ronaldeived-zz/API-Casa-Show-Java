package com.gft.gerenciador.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.gerenciador.domain.Casa;
import com.gft.gerenciador.domain.Evento;
import com.gft.gerenciador.repository.CasaRepository;
import com.gft.gerenciador.repository.EventoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventoServiceTest {
	
	@Autowired
	private EventoRepository repository;
	
	private Evento evento;
	
	private Casa casa;
	
	@Autowired
	private CasaRepository cRepository;
	
	@Before
	public void setup() {
		casa = new Casa("Casa", "Rua nova");
		cRepository.save(casa);
		evento = new Evento("Evento Teste", new Date() , 250.0 , 100.0 , casa);
	}
	
	@Test
	public void deveSalvarEventoComCasa() throws Exception {
		repository.save(evento);
		assertThat(evento.getId()).isNotNull();
		assertThat(evento.getNome()).isEqualTo("Evento Teste");
	}
	
	@Test
	public void deveDeletarEvento() throws Exception {
		repository.save(evento);
		repository.delete(evento);
		assertThat(repository.findById(evento.getId()).isEmpty());
	}
	
	@Test
	public void deveEditarEvento() throws Exception {
		repository.save(evento);
		evento.setNome("Fireflight");
		repository.save(evento);
		assertEquals("Fireflight", evento.getNome());
	}
	
	@Test
	public void deveReceberQtdEventosCadastradosNaCasa() throws Exception{
		Evento evento2 = new Evento("Fireflight", new Date() , 250.0 , 100.0 , casa);
		Evento evento3 = new Evento("Fireflight", new Date() , 250.0 , 100.0 , casa);
		List<Evento> eventos = new ArrayList<Evento>();
		eventos.add(evento);
		eventos.add(evento2);
		eventos.add(evento3);
		casa.setEventos(eventos);
		
//		for (int i = 0; i < eventos.size(); i++) {
//			System.out.println("+++++++++++???????????????"+ eventos.get(i).getNome());
//		}

		assertEquals(casa.getEventos().size() , eventos.size());
	}
	
}
