package br.com.lima.api.services;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.lima.api.entities.User;
import br.com.lima.api.repositories.UserRepository;
import br.com.lima.api.service.exceptions.DatabaseException;
import br.com.lima.api.service.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	// ENCONTRA
	public List<User> findAll() {
		return repository.findAll();
	}

	// ENCONTRA
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	// INSERE
	public User insert(User obj) {
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
	public User update(Long id, User obj) {    // GETONE INSTANCIA O USUÁRIO E O DEIXA ELE MONITORADO PELO JPA, NÃO VAI
		try {
		User entity = repository.getOne(id);   // DIRETAMENTE NO BD (COMO ACONTECE NO FINDBYID)
		updateData(entity, obj);
		return repository.save(entity);
	}catch(EntityNotFoundException e) {
		throw new ResourceNotFoundException(id);
	}
}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}

}
