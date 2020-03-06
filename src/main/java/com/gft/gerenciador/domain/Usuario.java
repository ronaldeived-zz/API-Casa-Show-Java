package com.gft.gerenciador.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Usuario {

	@Id
	@ApiModelProperty(example = "1")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O campo nome não pode ser vazio.")
	@ApiModelProperty(example = "Ronaldeived")
	private String nome;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Vendas> vendas;
	
	
	public List<Vendas> getVendas() {
		return vendas;
	}

	public void setVendas(List<Vendas> vendas) {
		this.vendas = vendas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
