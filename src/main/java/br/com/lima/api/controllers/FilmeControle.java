package br.com.lima.api.controllers;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lima.api.domains.Filme;
import br.com.lima.api.servicos.FilmeServico;

@RestController
@RequestMapping("/filmes")
public class FilmeControle {

	@Autowired
	private FilmeServico servico;

	@GetMapping
	public List<Filme> listar() {
		return servico.findAll();
	}

	
	@GetMapping(value = "/{id}") // REQUISIÇÃO IRÁ ACEITAR UM ID DENTRO DA URL
	public ResponseEntity<Filme> findById(@PathVariable Long id) {
		Filme obj = servico.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Filme> create(@Valid @RequestBody Filme filme) {
		Filme newObj = servico.create(filme);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	
	@DeleteMapping(value = "/{id}")    
	public ResponseEntity<Void> delete(@PathVariable Long id){
	 	servico.delete(id);
		return ResponseEntity.noContent().build();
	
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Filme> update(@PathVariable Long id, @RequestBody Filme filme){
		filme = servico.update(id, filme);
		return ResponseEntity.ok().body(filme);
	}
}
