package com.gft.gerenciador.service.exceptions;

public class CasaNaoEncontradaException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159807932108527864L;
	
	public CasaNaoEncontradaException (String mensagem) {
		super (mensagem);
	}
	public CasaNaoEncontradaException (String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
