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

import com.gft.gerenciador.domain.Casa;
import com.gft.gerenciador.service.CasaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@Api(tags = "Casas")
@RequestMapping(value = "/casas")
public class CasaResource {

	@Autowired
	private CasaService casaService;
	
	
	@ApiOperation("Lista as casas de show")
	@GetMapping()
	public ResponseEntity<List<Casa>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(casaService.listar());
	}
	
	@ApiOperation("Salva casa de show")
	@PostMapping
	public ResponseEntity<Void> salvar(
			@ApiParam(name = "Corpo", value = "Representação de uma casa")
			@Valid @RequestBody Casa casa) {
		
		casa = casaService.salvar(casa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(casa.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Busca casa de show")
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscar(
			@ApiParam(value = "ID de uma casa" , example = "1")
			@PathVariable("id") Long id) {
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		Casa casa = casaService.buscar(id).get();	
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(casa);
	}
	
	@ApiOperation("Deleta casa de show")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(
			@ApiParam(value = "ID de uma casa" , example = "1")
			@PathVariable("id") Long id) {
		
		casaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Atualiza casa de show")
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(
			@ApiParam(value = "ID de uma casa" , example = "1")
			@PathVariable ("id") Long id, 
			
			@ApiParam(name = "Corpo", value = "Representação de uma casa atualizado")
			@RequestBody Casa casa) {
		casa.setId(id);
		casaService.atualizar(casa);
		return ResponseEntity.noContent().build();
	}
	
	
	
}

