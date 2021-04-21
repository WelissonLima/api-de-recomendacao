package br.com.lima.api.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lima.api.modelo.FilmeResumoModelo;
import br.com.lima.api.modelo.RecomendacaoModelo;
import br.com.lima.dominio.modelo.Usuario;
import br.com.lima.dominio.repositorio.UsuarioRepositorio;
import br.com.lima.dominio.servico.SimilaridadeServico;

@Controller
@RequestMapping("/recomendacao")
public class RecomendacaoControle extends SimilaridadeServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@GetMapping("/{usuarioId}")
	public ResponseEntity<List<RecomendacaoModelo>> recomendacao(@PathVariable Long usuarioId) {

		List<Usuario> usuarios = usuarioRepositorio.findAll();
		List<RecomendacaoModelo> recomendacao = new ArrayList<>();
		Optional<Usuario> usuarioExistente = usuarioRepositorio.findById(usuarioId);

		if (usuarioExistente.isPresent()) {
			Usuario usuario1 = usuarioExistente.get();
			List<FilmeResumoModelo> listaFilmesUsuario1 = buscarFilmes(usuario1.getUsuarioFilmes());
			Double mediaNotas = getMediaDasNotas(listaFilmesUsuario1);

			for (Usuario usuario2 : usuarios) {
				if (!usuario1.equals(usuario2)) {
					List<FilmeResumoModelo> listaFilmesUsuario2 = buscarFilmes(usuario2.getUsuarioFilmes());

					Double similaridade = getEuclidiana(usuario1, usuario2);

					for (FilmeResumoModelo filme : listaFilmesUsuario2) {
						if (!listaFilmesUsuario1.contains(filme)) {
							Double nota = similaridade * filme.getNota();

							if (nota >= mediaNotas) {
								RecomendacaoModelo recomendacaoFilme = new RecomendacaoModelo(usuario2.getNome(),
										filme.getFilme().getNome(), nota);

								recomendacao.add(recomendacaoFilme);
							}
						}
					}
				}
			}

			return ResponseEntity.ok(recomendacao);
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
