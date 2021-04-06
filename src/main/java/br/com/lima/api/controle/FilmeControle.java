package br.com.lima.api.controle;

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

import br.com.lima.dominio.modelo.Filme;
import br.com.lima.dominio.repositorio.FilmeRepositorio;

@RestController
@RequestMapping("/filmes")
public class FilmeControle {

	@Autowired
	private FilmeRepositorio filmeRepositorio;

	@GetMapping
	public List<Filme> listar() {
		return filmeRepositorio.findAll();
	}

	@GetMapping("/{filmeId}")
	public ResponseEntity<Filme> buscar(@PathVariable Long filmeId) {
		Optional<Filme> filme = filmeRepositorio.findById(filmeId);

		if (filme.isPresent()) {
			return ResponseEntity.ok(filme.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping()
	public Filme adicionar(@Valid @RequestBody Filme filme) {
		Filme filmeExistente = filmeRepositorio.findByNome(filme.getNome());

		if (filmeExistente != null && !filmeExistente.equals(filme)) {
			throw new RuntimeException("Existe um filme cadastrado com esse nome");
		}

		return filmeRepositorio.save(filme);
	}

	@PutMapping("/{filmeId}")
	public ResponseEntity<Filme> atualizar(@Valid @PathVariable Long filmeId, @RequestBody Filme filme) {

		if (!filmeRepositorio.existsById(filmeId)) {
			return ResponseEntity.notFound().build();
		}

		filme.setId(filmeId);
		filme = filmeRepositorio.save(filme);

		return ResponseEntity.ok(filme);
	}

	@DeleteMapping("/{filmeId}")
	public ResponseEntity<Filme> deletar(@PathVariable Long filmeId) {

		if (!filmeRepositorio.existsById(filmeId)) {
			return ResponseEntity.notFound().build();
		}

		filmeRepositorio.deleteById(filmeId);

		return ResponseEntity.noContent().build();
	}
}
