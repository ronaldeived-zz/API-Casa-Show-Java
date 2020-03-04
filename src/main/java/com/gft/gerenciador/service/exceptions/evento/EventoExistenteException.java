package com.gft.gerenciador.service.exceptions.evento;

public class EventoExistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159807932108527864L;
	
	public EventoExistenteException (String mensagem) {
		super (mensagem);
	}
	public EventoExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
