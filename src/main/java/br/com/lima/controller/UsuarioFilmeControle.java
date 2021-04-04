package br.com.lima.controller;

import java.util.ArrayList;
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

import br.com.lima.entities.Filme;
import br.com.lima.entities.Usuario;
import br.com.lima.entities.UsuarioFilme;
import br.com.lima.model.UsuarioFilmeInputModelo;
import br.com.lima.model.UsuarioFilmeOutputModel;
import br.com.lima.repository.FilmeRepositorio;
import br.com.lima.repository.UsuarioFilmeRepositorio;
import br.com.lima.repository.UsuarioRepositorio;

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
	public List<UsuarioFilmeOutputModel> listar() {
		List<UsuarioFilme> usuarioFilmes = usuarioFilmeRepositorio.findAll();
		List<UsuarioFilmeOutputModel> usuarioFilmeOutputModels = new ArrayList<>();
		
		for (UsuarioFilme usFilme : usuarioFilmes) {
			UsuarioFilmeOutputModel usuarioFilmeOutputModel = new UsuarioFilmeOutputModel();
			usuarioFilmeOutputModel.setId(usFilme.getId());
			usuarioFilmeOutputModel.setFilme(usFilme.getFilme().getNome());
			usuarioFilmeOutputModel.setUsuario(usFilme.getUsuario().getNome());
			usuarioFilmeOutputModel.setNota(usFilme.getNota());
			
			usuarioFilmeOutputModels.add(usuarioFilmeOutputModel);
		}
		
		return usuarioFilmeOutputModels;
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
}
