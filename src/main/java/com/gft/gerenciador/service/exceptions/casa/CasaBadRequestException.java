package com.gft.gerenciador.service.exceptions.casa;

public class CasaBadRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159807932108527864L;
	
	public CasaBadRequestException (String mensagem) {
		super (mensagem);
	}
	public CasaBadRequestException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
