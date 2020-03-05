package com.gft.gerenciador.service.exceptions.vendas;

public class VendasExistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159807932108527864L;
	
	public VendasExistenteException (String mensagem) {
		super (mensagem);
	}
	public VendasExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
