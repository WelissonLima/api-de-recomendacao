package br.com.lima.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lima.api.domains.Usuario;
import br.com.lima.api.dtos.SimilaridadeOutputModelo;
import br.com.lima.api.repositorios.UsuarioRepositorio;
import br.com.lima.api.servicos.SimilaridadeServico;

@Controller
@RequestMapping("/similaridade")
public class SimilaridadeControle extends SimilaridadeServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@GetMapping("/{usuarioId}")
	public ResponseEntity<List<SimilaridadeOutputModelo>> listar(@PathVariable Long usuarioId) {
		Optional<Usuario> usuarioExistente1 = usuarioRepositorio.findById(usuarioId);
		List<Usuario> usuarios = usuarioRepositorio.findAll();

		if (usuarioExistente1.isPresent()) {
			Usuario usuario = usuarioExistente1.get();
			
			List<SimilaridadeOutputModelo> similaridadeUsuarios = getSimilaridadeGeral(usuario, usuarios);
			
			return ResponseEntity.ok(similaridadeUsuarios);
		}

		return ResponseEntity.notFound().build();
	}

}
