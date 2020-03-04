package com.gft.gerenciador.handler.CasaExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gft.gerenciador.domain.DetalhesErro;
import com.gft.gerenciador.service.exceptions.casa.CasaBadRequestException;
import com.gft.gerenciador.service.exceptions.casa.CasaExistenteException;
import com.gft.gerenciador.service.exceptions.casa.CasaNaoEncontradaException;

@ControllerAdvice
public class CasaResourceExceptionHandler {
	
	@ExceptionHandler(CasaNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handlerCasaNaoEncontradoException(CasaNaoEncontradaException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("A casa não pôde ser encontrado");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	@ExceptionHandler(CasaExistenteException.class)
	public ResponseEntity<DetalhesErro> handlerCasaExistenteException
	(CasaExistenteException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("Casa já existente");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(CasaBadRequestException.class)
	public ResponseEntity<DetalhesErro> handlerCasaBadRequestException
	(CasaBadRequestException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("Casa já existente");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DetalhesErro> handleMethodArgumentNotValidException
	(MethodArgumentNotValidException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida.");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
}
