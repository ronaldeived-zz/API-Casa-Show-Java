package com.gft.gerenciador.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.gerenciador.domain.Usuario;
import com.gft.gerenciador.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "Usuarios")
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation("Lista os usuarios")
	@GetMapping()
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listar());
	}
	
	@ApiOperation("Salva o usuario")
	@PostMapping
	public ResponseEntity<Void> salvar(
			@ApiParam(name = "Corpo", value = "Representação de um usuario")
			@Valid @RequestBody Usuario usuario) {
		
		usuario = usuarioService.salvar(usuario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Busca um usuario por id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscar(
			@ApiParam(value = "ID de um usuario" , example = "1")
			@PathVariable("id") Long id) {
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		Usuario usuario = usuarioService.buscar(id).get();	
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(usuario);
	}
	
	@ApiOperation("Deleta um usuario")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(
			@ApiParam(value = "ID de um usuario" , example = "1")
			@PathVariable("id") Long id) {
		
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Edita um usuario")
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(
			@ApiParam(value = "ID de um evento" , example = "1")
			@PathVariable ("id") Long id, 
			
			@ApiParam(name = "Corpo", value = "Representação de um usuario")
			@RequestBody Usuario usuario) {
		usuario.setId(id);
		usuarioService.atualizar(usuario);
		return ResponseEntity.noContent().build();
	}
}