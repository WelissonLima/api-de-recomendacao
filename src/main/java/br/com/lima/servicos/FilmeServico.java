package br.com.lima.servicos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.lima.domains.Filme;
import br.com.lima.repositorios.FilmeRepositorio;
import br.com.lima.servicos.exceptions.DatabaseException;
import br.com.lima.servicos.exceptions.ResourceNotFoundException;

@Service
public class FilmeServico {

	@Autowired
	FilmeRepositorio repositorio;

	public List<Filme> findAll() {
		return repositorio.findAll();
	}

	public Filme findById(Long id) {
		Optional<Filme> film = repositorio.findById(id);
		return film.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Filme create(Filme filme) {
		return repositorio.save(new Filme(null, filme.getNome(), filme.getSinopse()));
			
	}

	public void delete(Long id) {
		findById(id);
		try {
			repositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Filme não pode ser deletado! Possui Usuarios associados");
		}
	}


	public Filme update(Long id, Filme obj){
		try {
			Filme entidade = repositorio.getOne(id);
			updateDate(entidade, obj);				
			return repositorio.save(entidade);			
			
			
		} catch (EntityNotFoundException e) {
			
			throw new ResourceNotFoundException(id);
		}

	}
	
	private void updateDate(Filme entidade, Filme obj) {
		entidade.setNome(obj.getNome());
	}
}
