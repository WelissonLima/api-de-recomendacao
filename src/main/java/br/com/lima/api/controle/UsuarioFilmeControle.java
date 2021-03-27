package br.com.lima.api.controle;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lima.api.modelo.UsuarioFilmeInputModelo;
import br.com.lima.api.modelo.UsuarioFilmeOutputModel;
import br.com.lima.dominio.modelo.Filme;
import br.com.lima.dominio.modelo.Usuario;
import br.com.lima.dominio.modelo.UsuarioFilme;
import br.com.lima.dominio.repositorio.FilmeRepositorio;
import br.com.lima.dominio.repositorio.UsuarioFilmeRepositorio;
import br.com.lima.dominio.repositorio.UsuarioRepositorio;

@RestController
@RequestMapping("/usuarios-filmes")
public class UsuarioFilmeControle {

	@Autowired
	private UsuarioFilmeRepositorio usuarioFilmeRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private FilmeRepositorio filmeRepositorio;

	@GetMapping()
	public List<UsuarioFilme> listar() {
		return usuarioFilmeRepositorio.findAll();
	}

	@GetMapping("/{usuarioFilmeId}")
	public ResponseEntity<UsuarioFilmeOutputModel> buscar(@PathVariable Long usuarioFilmeId) {
		Optional<UsuarioFilme> usuarioFilmeExistente = usuarioFilmeRepositorio.findById(usuarioFilmeId);

		if (usuarioFilmeExistente.isPresent()) {
			UsuarioFilme usuarioFilme = usuarioFilmeExistente.get();
			UsuarioFilmeOutputModel usuarioFilmeOutputModel = new UsuarioFilmeOutputModel();
			
			usuarioFilmeOutputModel.setId(usuarioFilmeId);
			usuarioFilmeOutputModel.setFilme(usuarioFilme.getFilme().getNome());
			usuarioFilmeOutputModel.setUsuario(usuarioFilme.getUsuario().getNome());
			usuarioFilmeOutputModel.setNota(usuarioFilme.getNota());
			
			return ResponseEntity.ok(usuarioFilmeOutputModel);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<UsuarioFilme> adicionar(@Valid @RequestBody UsuarioFilmeInputModelo usuarioFilmeInputModelo) {
		Optional<Usuario> usuario = usuarioRepositorio.findById(usuarioFilmeInputModelo.getUsuario_id());
		Optional<Filme> filme = filmeRepositorio.findById(usuarioFilmeInputModelo.getFilme_id());

		if (usuario.isPresent() && filme.isPresent()) {
			UsuarioFilme usuarioFilme = new UsuarioFilme();
			
			usuarioFilme.setFilme(filme.get());
			usuarioFilme.setUsuario(usuario.get());
			usuarioFilme.setNota(usuarioFilmeInputModelo.getNota());
			
			filme.get().getUsuarioFilmes().add(usuarioFilme);
			usuario.get().getUsuarioFilmes().add(usuarioFilme);
			
			usuarioFilmeRepositorio.save(usuarioFilme);

			return ResponseEntity.ok(usuarioFilme);
		}		

		return ResponseEntity.notFound().build();
	}
	
//	@PutMapping("/{usuarioId}")
//	public ResponseEntity<Usuario> atualizar(@Valid @PathVariable Long usuarioId, @RequestBody Usuario usuario) {
//
//		if (!usuarioFilmeRepositorio.existsById(usuarioId)) {
//			return ResponseEntity.notFound().build();
//		}
//
//		usuario.setId(usuarioId);
//		usuario = usuarioFilmeRepositorio.save(usuario);
//
//		return ResponseEntity.ok(usuario);
//	}
//	
//	@DeleteMapping("/{usuarioId}")
//	public ResponseEntity<Usuario> deletar(@PathVariable Long usuarioId){
//		
//		if(!usuarioFilmeRepositorio.existsById(usuarioId)) {
//			throw new RuntimeException("Esse usuário não existe.");
//		}
//		
//		usuarioFilmeRepositorio.deleteById(usuarioId);
//		
//		return ResponseEntity.noContent().build();
//	}
}
