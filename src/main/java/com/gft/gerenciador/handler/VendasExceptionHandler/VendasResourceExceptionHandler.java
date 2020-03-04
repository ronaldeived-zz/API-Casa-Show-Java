package com.gft.gerenciador.handler.VendasExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gft.gerenciador.domain.DetalhesErro;
import com.gft.gerenciador.service.exceptions.evento.EventoExistenteException;
import com.gft.gerenciador.service.exceptions.vendas.VendasNaoEncontradoException;

@ControllerAdvice
public class VendasResourceExceptionHandler {

	@ExceptionHandler(VendasNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handlerVendasNaoEncontradoException(VendasNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("A venda não pôde ser encontrado");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

	}
	
	
	@ExceptionHandler(EventoExistenteException.class)
	public ResponseEntity<DetalhesErro> handlerEventoExistenteException
	(EventoExistenteException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("Evento já existente");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
}
