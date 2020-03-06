package com.gft.gerenciador.handler.VendasExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.gft.gerenciador.domain.DetalhesErro;
import com.gft.gerenciador.service.exceptions.vendas.VendasExistenteException;
import com.gft.gerenciador.service.exceptions.vendas.VendasNaoEncontradoException;

@ControllerAdvice
public class VendasResourceExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(VendasNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handlerVendasNaoEncontradoException(VendasNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("A venda não pôde ser encontrado");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

	}
	
	
	@ExceptionHandler(VendasExistenteException.class)
	public ResponseEntity<DetalhesErro> handlerVendasExistenteException
	(VendasExistenteException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("Venda já existente");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException
	(DataIntegrityViolationException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida.");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<DetalhesErro>> handlerMethodArgumentNotValidException
	(MethodArgumentNotValidException e, HttpServletRequest request){
		
		List<DetalhesErro> erro = new ArrayList();

		List<FieldError> erros = e.getBindingResult().getFieldErrors();
		
		erros.forEach(error -> {
			DetalhesErro erroAtual = new DetalhesErro();
			erroAtual.setStatus(400l);
			erroAtual.setTitulo("Requisição inválida.");
			erroAtual.setTimestamp(System.currentTimeMillis());
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			erroAtual.setMessage(message);
			erro.add(erroAtual);
		});
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<DetalhesErro> handlerInvalidFormatException
	(InvalidFormatException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida.");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(MismatchedInputException.class)
	public ResponseEntity<DetalhesErro> handlerMismatchedInputException
	(MismatchedInputException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida.");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<DetalhesErro> handlerHttpRequestMethodNotSupportedException
	(HttpRequestMethodNotSupportedException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida. Não pode alterar sem colocar um id na URI.");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<DetalhesErro> handlerEntityNotFoundException
	(EntityNotFoundException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida. Não pode alterar os dados do evento ou usuario.");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<DetalhesErro> handlerMethodArgumentTypeMismatchException
	(MethodArgumentTypeMismatchException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida. Digite corretamente a URI.");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
