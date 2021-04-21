package br.com.lima.api.controle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import br.com.lima.api.modelo.FilmeOutput;
import br.com.lima.api.modelo.FilmeResumoModelo;
import br.com.lima.api.modelo.UsuarioOutputModel;
import br.com.lima.dominio.modelo.Usuario;
import br.com.lima.dominio.modelo.UsuarioFilme;
import br.com.lima.dominio.repositorio.UsuarioRepositorio;

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
	public ResponseEntity<UsuarioOutputModel> buscar(@PathVariable Long usuarioId) {
		UsuarioOutputModel usuarioOutput = new UsuarioOutputModel();
		Optional<Usuario> usuarioExistente = usuarioRepositorio.findById(usuarioId);

		if (usuarioExistente.isPresent()) {
			Usuario usuario = usuarioExistente.get();
			Set<FilmeOutput> filmes = formartarSaidaDeFilmes(usuario);

			usuarioOutput.setId(usuarioId);
			usuarioOutput.setNome(usuario.getNome());
			usuarioOutput.setFilmes(filmes);

			return ResponseEntity.ok(usuarioOutput);
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
	public ResponseEntity<Usuario> deletar(@PathVariable Long usuarioId) {

		if (!usuarioRepositorio.existsById(usuarioId)) {
			throw new RuntimeException("Esse usuário não existe.");
		}

		usuarioRepositorio.deleteById(usuarioId);

		return ResponseEntity.noContent().build();
	}

	// Service
	private List<FilmeResumoModelo> buscarFilmes(Set<UsuarioFilme> usuarioFilmes) {
		List<FilmeResumoModelo> filmes = new ArrayList<>();

		for (UsuarioFilme usuarioFilme : usuarioFilmes) {
			FilmeResumoModelo filmeResumoModelo = new FilmeResumoModelo();
			filmeResumoModelo.setNota(usuarioFilme.getNota());
			filmeResumoModelo.setFilme(usuarioFilme.getFilme());

			filmes.add(filmeResumoModelo);
		}

		return filmes;
	}

	private Set<FilmeOutput> formartarSaidaDeFilmes(Usuario usuario) {
		Set<FilmeOutput> filmesFormatados = new HashSet<FilmeOutput>();
		List<FilmeResumoModelo> filmes = buscarFilmes(usuario.getUsuarioFilmes());

		for (FilmeResumoModelo filme : filmes) {
			filmesFormatados.add(new FilmeOutput(filme.getFilme().getNome(), filme.getNota()));
		}

		return filmesFormatados;
	}

}
