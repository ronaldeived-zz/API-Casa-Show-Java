package com.gft.gerenciador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.gerenciador.domain.Vendas;
import com.gft.gerenciador.repository.VendasRepository;
import com.gft.gerenciador.service.exceptions.vendas.VendasNaoEncontradoException;

@Service
public class VendasService {

	@Autowired
	private VendasRepository vendasRepository;
	
	public List<Vendas> listar(){
		return vendasRepository.findAll();
	}
	
	public Optional<Vendas> buscar(Long id) {
		Optional<Vendas> vendas = vendasRepository.findById(id);
		
		if (vendas.isEmpty()) {
			throw new VendasNaoEncontradoException("A venda não foi encontrado.");
		}
		vendas.get();
		return vendas;
	}
	
	public Vendas salvar(Vendas vendas) {
		vendas.setId(null);
		return vendasRepository.save(vendas);
	}
	
	public void deletar(Long id) {
		try {
			vendasRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new VendasNaoEncontradoException("A venda não pôde ser encontrado.");
		}
		
	}
	
	public void atualizar(Vendas vendas) {
		verificarExistencia(vendas);
		vendasRepository.save(vendas);
	}
	
	private void verificarExistencia(Vendas vendas) {
		buscar(vendas.getId());
	}
	
}
