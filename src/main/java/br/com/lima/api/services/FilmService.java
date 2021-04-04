package br.com.lima.api.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.lima.api.entities.Film;
import br.com.lima.api.repositories.FilmRepository;
import br.com.lima.api.service.exceptions.DatabaseException;
import br.com.lima.api.service.exceptions.ResourceNotFoundException;

@Service
public class FilmService {

	@Autowired
	private FilmRepository repository;

	// ENCONTRA
	public List<Film> findAll() {
		return repository.findAll();
	}

	// ENCONTRA
	public Film findById(Long id) {
		Optional<Film> obj = repository.findById(id);
		return obj.get();
	}

	// INSERE
	public Film insert(Film obj) {
		return repository.save(obj);
	}

	// DELETA
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	// ATUALIZA
	public Film update(Long id, Film obj) { // GETONE INSTANCIA O USUÁRIO E O DEIXA ELE MONITORADO PELO JPA, NÃO VAI
		try {
			Film entity = repository.getOne(id); // DIRETAMENTE NO BD (COMO ACONTECE NO FINDBYID)
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(Film entity, Film obj) {

		entity.setId(obj.getId());
		entity.setName(obj.getName());
		entity.setCategory(obj.getCategory());

	}

}
