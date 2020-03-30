package com.gft.gerenciador.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.gerenciador.domain.Casa;
import com.gft.gerenciador.domain.Evento;
import com.gft.gerenciador.domain.Usuario;
import com.gft.gerenciador.domain.Vendas;
import com.gft.gerenciador.repository.CasaRepository;
import com.gft.gerenciador.repository.EventoRepository;
import com.gft.gerenciador.repository.UsuarioRepository;
import com.gft.gerenciador.repository.VendasRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VendasServiceTest {

	private Vendas venda;
	
	@Autowired
	private VendasRepository vRepository;
	
	private Casa casa;
	
	@Autowired
	private CasaRepository cRepository;
	
	private Usuario usuario;
	
	@Autowired
	private UsuarioRepository uRepository;
	
	private Evento evento;
	
	@Autowired
	private EventoRepository eRepository;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		casa = new Casa("Casa de Show", "Rio Brancko");
		usuario = new Usuario("Ronaldeived");
		evento = new Evento("Fireflight", new Date(), 1000.00, 150.0, casa);
		venda = new Vendas(10.0, evento, usuario);
	}
	
	@Test
	public void deveSalvarVendaComTodosAtributos() {
		cRepository.save(casa);
		uRepository.save(usuario);
		eRepository.save(evento);
		vRepository.save(venda);
		
		assertThat(venda.getId()).isNotNull();
		assertThat(vRepository.findById(venda.getId()).isPresent());
	}
	
	@Test
	public void deveDeletarVenda() throws Exception {
		cRepository.save(casa);
		uRepository.save(usuario);
		eRepository.save(evento);
		vRepository.save(venda);
		vRepository.delete(venda);
		assertThat(vRepository.findById(evento.getId()).isEmpty());
	}
	
	@Test
	public void deveRetornarExceptionQuandoSalvarSemUsuario() throws Exception {
		
	}
	
	@Test
	public void deveEditarEvento() throws Exception {
		cRepository.save(casa);
		uRepository.save(usuario);
		eRepository.save(evento);
		vRepository.save(venda);
		venda.setQuantidade(8.0);
		assertEquals(8.0 , venda.getQuantidade(), 0.01);
	}
}
