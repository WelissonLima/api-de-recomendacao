package br.com.lima.api.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lima.api.domains.Usuario;
import br.com.lima.api.dtos.UsuarioOutputModel;
import br.com.lima.api.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio repositorio;

	public List<Usuario> findAll() {
		return repositorio.findAll();
	}

	public Usuario findById(Long id) {
		Optional<Usuario> user = repositorio.findById(id);
		return user.orElseThrow();
	}

	public Usuario create(UsuarioOutputModel objDTO) {

		return repositorio.save(new Usuario(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getTelefone()));

	}

	public void delete(Long id) { 
		repositorio.deleteById(id);
	}

	public Usuario update(Long id, Usuario obj) {
		Usuario entidade = repositorio.getOne(id); 
		updateDate(entidade, obj); 
		return repositorio.save(entidade); 

	}

	private void updateDate(Usuario entidade, Usuario obj) {
		entidade.setNome(obj.getNome());
		entidade.setEmail(obj.getEmail());
		entidade.setTelefone(obj.getTelefone());
		
	}
}
