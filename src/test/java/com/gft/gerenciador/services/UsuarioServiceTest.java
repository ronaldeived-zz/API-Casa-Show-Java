package com.gft.gerenciador.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gft.gerenciador.domain.Usuario;
import com.gft.gerenciador.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioServiceTest {
	
	private Usuario usuario;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Before
	public void setup() {
		usuario = new Usuario("Ronaldeived");
	}
	
	@Test
	public void deveSalvarUsuario() {
		repository.save(usuario);
		
		assertEquals("Ronaldeived", usuario.getNome());
		assertThat(usuario.getId()).isNotNull();
	}
	
	@Test
	public void deveEditarUsuario() {
		repository.save(usuario);
		
		usuario.setNome("Jõao");
		repository.save(usuario);
		
		assertEquals(usuario.getNome(), "Jõao");
	}
	
	@Test
	public void deveDeletarUsuario() {
		repository.save(usuario);
		
		repository.delete(usuario);
		
		assertThat(repository.findById(usuario.getId()).isEmpty());
	}
}
