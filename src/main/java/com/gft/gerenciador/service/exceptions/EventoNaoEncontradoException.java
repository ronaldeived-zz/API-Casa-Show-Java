package com.gft.gerenciador.service.exceptions;

public class EventoNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159807932108527864L;
	
	public EventoNaoEncontradoException (String mensagem) {
		super (mensagem);
	}
	public EventoNaoEncontradoException (String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
