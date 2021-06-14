package br.com.lima.servicos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.lima.domains.Usuario;
import br.com.lima.dtos.UsuarioOutputModel;
import br.com.lima.repositorios.UsuarioRepositorio;
import br.com.lima.servicos.exceptions.ResourceNotFoundException;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio repositorio;

	public List<Usuario> findAll() {
		return repositorio.findAll();
	}

	public Usuario findById(Long id) {
		Optional<Usuario> user = repositorio.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Usuario create(UsuarioOutputModel objDTO) {

		return repositorio.save(new Usuario(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getTelefone()));

	}

	public void delete(Long id) { 
		findById(id);
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new br.com.lima.servicos.exceptions.DataIntegrityViolationException(
					"Usuário não pode ser deletado! Possui filmes associados");
		}
	}
	
	public Usuario update(Long id, Usuario obj) {
		try {
			Usuario entidade = repositorio.getOne(id); 
			updateDate(entidade, obj);				
			return repositorio.save(entidade);			
			
			
		} catch (EntityNotFoundException e) {
			
			throw new ResourceNotFoundException(id);
		}

	}
	
	private void updateDate(Usuario entidade, Usuario obj) {
		entidade.setNome(obj.getNome());
		entidade.setEmail(obj.getEmail());
		entidade.setTelefone(obj.getTelefone());
		
	}
}
