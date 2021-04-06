package br.com.lima.api.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lima.api.entities.User;
import br.com.lima.api.repositories.UserRepository;
import br.com.lima.api.services.SimilarityService;
import br.com.lima.model.SimilaridadeOutputModelo;

@Controller
@RequestMapping("/similarity")
public class SimilarityResource extends SimilarityService{

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/{usuarioId}")
	public ResponseEntity<List<SimilaridadeOutputModelo>> listar(@PathVariable Long usuarioId) {
		Optional<User> usuarioExistente1 = userRepository.findById(usuarioId);
		List<User> usuarios = userRepository.findAll();

		if (usuarioExistente1.isPresent()) {
			User usuario = usuarioExistente1.get();
			
			List<SimilaridadeOutputModelo> similaridadeUsuarios = getSimilaridadeGeral(usuario, usuarios);
			
			return ResponseEntity.ok(similaridadeUsuarios);
		}

		return ResponseEntity.notFound().build();
	}

}
