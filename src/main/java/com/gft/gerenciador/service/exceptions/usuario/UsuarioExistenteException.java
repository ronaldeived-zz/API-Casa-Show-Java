package com.gft.gerenciador.service.exceptions.usuario;

public class UsuarioExistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159807932108527864L;
	
	public UsuarioExistenteException (String mensagem) {
		super (mensagem);
	}
	public UsuarioExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
