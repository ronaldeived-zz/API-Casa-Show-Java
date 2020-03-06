package com.gft.gerenciador.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Evento {

	@Id
	@ApiModelProperty(example = "1")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(example = "Legiao Urbana")
	@NotEmpty(message = "O campo nome não pode ser vazio.")
	private String nome;
	
	@ApiModelProperty(example = "20/02/2020")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo data não pode ser vazio.")
	private Date data;
	
	@ApiModelProperty(example = "2500")
	@NotNull(message = "O campo capacidade não pode ser vazio.")
	private Double capacidade;
	
	@NotNull(message = "O campo preço não pode ser vazio.")
	@ApiModelProperty(example = "100.00")
	private Double preco;
	
	@ManyToOne()
	@NotNull(message = "O campo casa não pode ser vazio")
	@JoinColumn(name = "CASA_ID")
	private Casa casa;
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Vendas> vendas;

	
	public Double getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Double capacidade) {
		this.capacidade = capacidade;
	}

	public List<Vendas> getVendas() {
		return vendas;
	}

	public void setVendas(List<Vendas> vendas) {
		this.vendas = vendas;
	}

	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
