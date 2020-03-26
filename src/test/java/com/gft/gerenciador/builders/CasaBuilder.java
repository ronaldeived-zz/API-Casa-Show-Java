package com.gft.gerenciador.builders;

import com.gft.gerenciador.domain.Casa;

public class CasaBuilder {

	private Casa casa;
	
	private CasaBuilder() {}
	
	public static CasaBuilder umaCasa() {
		CasaBuilder builder = new CasaBuilder();
		builder.casa = new Casa();
		builder.casa.setNome("Casa Euphoria");
		builder.casa.setEndereco("Bandeirantes");
		
		return builder;
	}
	
	public Casa agora() {
		return casa;
	}
}
