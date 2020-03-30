package com.gft.gerenciador.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;


@Entity
public class Vendas {

	@Id
	@ApiModelProperty(example = "1")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(example = "10")
	@NotNull(message = "O campo quantidade não poder ser vazio.")
	@JsonInclude(Include.NON_NULL)
	private Double quantidade;
	
	@ManyToOne
	@NotNull(message = "O campo evento não pode ser vazio.")
	@JoinColumn(name = "EVENTO_ID")
	private Evento evento;
	
	@ManyToOne
	@NotNull(message ="O campo usuario não pode ser vazio.")
	@JoinColumn(name = "USUARIO_ID")
	private Usuario usuario;
	
	public Vendas () {}
	
	public Vendas(@NotNull(message = "O campo quantidade não poder ser vazio.") Double quantidade,
			@NotNull(message = "O campo evento não pode ser vazio.") Evento evento,
			@NotNull(message = "O campo usuario não pode ser vazio.") Usuario usuario) {
		super();
		this.quantidade = quantidade;
		this.evento = evento;
		this.usuario = usuario;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
