package br.com.lima.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lima.entities.Usuario;
import br.com.lima.repository.UsuarioRepositorio;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControle {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@GetMapping()
	public List<Usuario> listar() {
		return usuarioRepositorio.findAll();
	}

	@GetMapping("/{usuarioId}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long usuarioId) {
		Optional<Usuario> usuario = usuarioRepositorio.findById(usuarioId);

		if (usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public Usuario adicionar(@Valid @RequestBody Usuario usuario) {
		Usuario usuarioExistente = usuarioRepositorio.findByEmail(usuario.getEmail());

		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new RuntimeException("Existe um usuário cadastrado com esse email.");
		}

		return usuarioRepositorio.save(usuario);
	}
	
	@PutMapping("/{usuarioId}")
	public ResponseEntity<Usuario> atualizar(@Valid @PathVariable Long usuarioId, @RequestBody Usuario usuario) {

		if (!usuarioRepositorio.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}

		usuario.setId(usuarioId);
		usuario = usuarioRepositorio.save(usuario);

		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Usuario> deletar(@PathVariable Long usuarioId){
		
		if(!usuarioRepositorio.existsById(usuarioId)) {
			throw new RuntimeException("Esse usuário não existe.");
		}
		
		usuarioRepositorio.deleteById(usuarioId);
		
		return ResponseEntity.noContent().build();
	}
}




