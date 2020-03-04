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

import com.gft.gerenciador.domain.Evento;
import com.gft.gerenciador.service.EventoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "Eventos")
@RequestMapping("/eventos")
public class EventoResource {

	@Autowired
	private EventoService eventoService;
	
	@ApiOperation("Lista os eventos")
	@GetMapping()
	public ResponseEntity<List<Evento>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listar());
	}
	
	@ApiOperation("Salva o evento")
	@PostMapping
	public ResponseEntity<Void> salvar(
			@ApiParam(name = "Corpo", value = "Representação de um um evento")
			@Valid @RequestBody Evento evento) {
		
		evento = eventoService.salvar(evento);
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(evento.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Busca o evento por id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscar(
			@ApiParam(value = "ID de um evento" , example = "1")
			@PathVariable("id") Long id) {
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		Evento evento = eventoService.buscar(id).get();	
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(evento);
	}
	
	@ApiOperation("Deleta o evento")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(
			@ApiParam(value = "ID de um evento" , example = "1")
			@PathVariable("id") Long id) {
		
		eventoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Atualiza o evento")
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(
			@ApiParam(value = "ID de um evento" , example = "1")
			@PathVariable ("id") Long id, 
			
			@ApiParam(name = "Corpo", value = "Representação de um evento")
			@RequestBody Evento evento) {
		evento.setId(id);
		eventoService.atualizar(evento);
		return ResponseEntity.noContent().build();
	}
}
