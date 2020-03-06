package com.gft.gerenciador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.gerenciador.domain.Casa;
import com.gft.gerenciador.domain.Usuario;
import com.gft.gerenciador.repository.UsuarioRepository;
import com.gft.gerenciador.service.exceptions.casa.CasaExistenteException;
import com.gft.gerenciador.service.exceptions.usuario.UsuarioExistenteException;
import com.gft.gerenciador.service.exceptions.usuario.UsuarioNaoEncontradoException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarios;
	
	public List<Usuario> listar(){
		return usuarios.findAll();
	}
	
	public Optional<Usuario> buscar(Long id) {
		Optional<Usuario> usuario = usuarios.findById(id);
		
		if (usuario.isEmpty()) {
			throw new UsuarioNaoEncontradoException("O usuario não foi encontrado.");
		}
		usuario.get();
		return usuario;
	}
	
	public Usuario salvar(Usuario usuario) {
		
		Usuario usuarioNome = usuarios.findByNome(usuario.getNome());
		if(usuarioNome != null) {
			throw new UsuarioExistenteException("O usuario já existe");
		}
		
		if (usuario.getId() != null) {
			Optional<Usuario> a = usuarios.findById(usuario.getId());
			
			if (a.isPresent()) {
				throw new UsuarioExistenteException("O usuario já existe");
			}
		}
		return usuarios.save(usuario);
	}
	
	public void deletar(Long id) {
		try {
			usuarios.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException("O usuario não pôde ser encontrado.");
		}
	}
	
	public void atualizar(Usuario usuario) {
		verificarExistencia(usuario);
		usuarios.save(usuario);
	}
	
	private void verificarExistencia(Usuario usuario) {
		buscar(usuario.getId());
	}
	
}
