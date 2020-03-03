package com.gft.gerenciador.service.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159807932108527864L;
	
	public UsuarioNaoEncontradoException (String mensagem) {
		super (mensagem);
	}
	public UsuarioNaoEncontradoException (String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
