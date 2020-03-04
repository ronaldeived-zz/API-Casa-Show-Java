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

import com.gft.gerenciador.domain.Vendas;
import com.gft.gerenciador.service.VendasService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "Vendas")
@RequestMapping("/vendas")
public class VendasResource {

	@Autowired
	private VendasService vendasService;

	@ApiOperation("Lista as vendas")
	@GetMapping()
	public ResponseEntity<List<Vendas>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(vendasService.listar());
	}
	
	@ApiOperation("Salva a venda")
	@PostMapping
	public ResponseEntity<Void> salvar(
			@ApiParam(name = "Corpo", value = "Representação de uma venda")
			@Valid @RequestBody Vendas vendas) {
		
		vendas = vendasService.salvar(vendas);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(vendas.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Busca a venda por id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscar(
			@ApiParam(value = "ID de uma venda" , example = "1")
			@PathVariable("id") Long id) {
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		Vendas vendas = vendasService.buscar(id).get();	
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(vendas);
	}
	
	@ApiOperation("Deleta a venda")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(
			@ApiParam(value = "ID de uma venda" , example = "1")
			@PathVariable("id") Long id) {
		
		vendasService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Atualiza a venda")
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(
			@ApiParam(value = "ID de uma venda" , example = "1")
			@PathVariable ("id") Long id, 
			
			@ApiParam(name = "Corpo", value = "Representação de uma casa")
			@RequestBody Vendas vendas) {
		vendas.setId(id);
		vendasService.atualizar(vendas);
		return ResponseEntity.noContent().build();
	}
}