package br.com.lima.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lima.domains.Usuario;
import br.com.lima.dtos.UsuarioOutputModel;
import br.com.lima.servicos.UsuarioServico;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioControle {

	@Autowired
	private UsuarioServico servico;

	@GetMapping()
	public ResponseEntity<List<UsuarioOutputModel>> findAll() {
		List<UsuarioOutputModel> listar = servico.findAll().stream().map(obj -> new UsuarioOutputModel(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listar);
	}
	

	@GetMapping(value = "/{id}") 
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario obj = servico.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@PostMapping
	public ResponseEntity<UsuarioOutputModel> create(@Valid @RequestBody UsuarioOutputModel objDTO) {
		Usuario newObj = servico.create(objDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	
	@DeleteMapping(value = "/{id}")    
	public ResponseEntity<Void> delete(@PathVariable Long id){
	 	servico.delete(id);
		return ResponseEntity.noContent().build();
	
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
		usuario = servico.update(id, usuario);
		return ResponseEntity.ok().body(usuario);
	}
	

}
