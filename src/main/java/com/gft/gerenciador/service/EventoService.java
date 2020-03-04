package com.gft.gerenciador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
}
