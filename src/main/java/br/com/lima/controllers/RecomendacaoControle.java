package br.com.lima.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lima.domains.Usuario;
import br.com.lima.dtos.FilmeResumoModelo;
import br.com.lima.dtos.RecomendacaoModelo;
import br.com.lima.repositorios.UsuarioRepositorio;
import br.com.lima.servicos.SimilaridadeServico;

@CrossOrigin("*")
@Controller
@RequestMapping("/recomendacao")
public class RecomendacaoControle extends SimilaridadeServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@GetMapping("/{usuarioId}")
	public ResponseEntity<List<RecomendacaoModelo>> recomendacao(@PathVariable Long usuarioId) {

		List<Usuario> usuarios = usuarioRepositorio.findAll();
		List<RecomendacaoModelo> recomendacoes = new ArrayList<>();
		Optional<Usuario> usuarioExistente = usuarioRepositorio.findById(usuarioId);

		if (usuarioExistente.isPresent()) {

			Usuario usuario1 = usuarioExistente.get();
			List<FilmeResumoModelo> listaFilmesUsuario1 = buscarFilmes(usuario1.getFilmes());
			Double mediaNotas = getMediaDasNotas(listaFilmesUsuario1);

			for (Usuario usuario2 : usuarios) {
				if (!usuario1.equals(usuario2)) {
					List<FilmeResumoModelo> listaFilmesUsuario2 = buscarFilmes(usuario2.getFilmes());

					Double similaridade = getEuclidiana(usuario1, usuario2);

					listaFilmesUsuario2.stream().filter(filme1 -> !listaFilmesUsuario1.contains(filme1))
							.filter(filme2 -> filme2.getNota() * similaridade >= mediaNotas)
							.forEach(filme3 -> recomendacoes.add(new RecomendacaoModelo(usuario2.getNome(),
									filme3.getFilme().getNome(), filme3.getNota() * similaridade)));
				}
			}

			return ResponseEntity.ok(recomendacoes);
		}

		return ResponseEntity.notFound().build();
	}

	private double getMediaDasNotas(List<FilmeResumoModelo> filmes) {

		if (filmes.size() == 0) {
			return 0;
		}

		Double media = filmes.stream().mapToDouble(filme -> filme.getNota()).sum();
		return (media / filmes.size()) * 0.9;
	}
}
