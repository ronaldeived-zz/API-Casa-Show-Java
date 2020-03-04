package com.gft.gerenciador.service.exceptions.casa;

public class CasaExistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159807932108527864L;
	
	public CasaExistenteException (String mensagem) {
		super (mensagem);
	}
	public CasaExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
