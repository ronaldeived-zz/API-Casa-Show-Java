package com.gft.gerenciador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gft.gerenciador.domain.Evento;
import com.gft.gerenciador.repository.EventoRepository;
import com.gft.gerenciador.service.exceptions.evento.EventoExistenteException;
import com.gft.gerenciador.service.exceptions.evento.EventoNaoEncontradoException;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	public List<Evento> listar(){
		return eventoRepository.findAll();
	}
	
	public Optional<Evento> buscar(Long id) {
		Optional<Evento> evento = eventoRepository.findById(id);
		
		if (evento.isEmpty()) {
			throw new EventoNaoEncontradoException("O livro não foi encontrado.");
		}
		evento.get();
		return evento;
	}
	
	public Evento salvar(Evento evento) {
		if (evento.getId() != null) {
			Optional<Evento> a = eventoRepository.findById(evento.getId());
			
			if (a.isPresent()) {
				throw new EventoExistenteException("O evento já existe");
			}
		}
		return eventoRepository.save(evento);
	}
	
	public void deletar(Long id) {
		try {
			eventoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EventoNaoEncontradoException("O evento não pôde ser encontrado.");
		}
		
	}
	
	public void atualizar(Evento evento) {
		verificarExistencia(evento);
		eventoRepository.save(evento);
	}
	
	private void verificarExistencia(Evento evento) {
		buscar(evento.getId());
	}
	
	public List<Evento> listarCrescenteNome(){
		return eventoRepository.findAll(Sort.by(Sort.Direction.ASC,"nome"));
	}

	public List<Evento> listarDecrecenteNome(){
		return eventoRepository.findAll(Sort.by(Sort.Direction.DESC,"nome"));
	}
	
	public List<Evento> listarCrescenteCapacidade(){
		return eventoRepository.findAll(Sort.by(Sort.Direction.ASC,"capacidade"));
	}

	public List<Evento> listarDecrecenteCapacidade(){
		return eventoRepository.findAll(Sort.by(Sort.Direction.DESC,"capacidade"));
	}
	
	public List<Evento> listarCrescenteData(){
		return eventoRepository.findAll(Sort.by(Sort.Direction.ASC,"data"));
	}

	public List<Evento> listarDecrecenteData(){
		return eventoRepository.findAll(Sort.by(Sort.Direction.DESC,"data"));
	}
	
	public List<Evento> listarCrescentePreco(){
		return eventoRepository.findAll(Sort.by(Sort.Direction.ASC,"preco"));
	}

	public List<Evento> listarDecrecentePreco(){
		return eventoRepository.findAll(Sort.by(Sort.Direction.DESC,"preco"));
	}
	
	public Evento buscarPorNome(String nome){
		 Evento evento = eventoRepository.findByNome(nome);
		return evento;
	}
}
