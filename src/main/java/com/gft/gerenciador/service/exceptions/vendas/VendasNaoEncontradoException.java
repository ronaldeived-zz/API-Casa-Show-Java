package com.gft.gerenciador.service.exceptions.vendas;

public class VendasNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159807932108527864L;
	
	public VendasNaoEncontradoException (String mensagem) {
		super (mensagem);
	}
	public VendasNaoEncontradoException (String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
