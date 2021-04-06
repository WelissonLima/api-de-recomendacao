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
import br.com.lima.dominio.repositorio.FilmeRepositorio;
import br.com.lima.dominio.repositorio.UsuarioRepositorio;
import br.com.lima.dominio.servico.SimilaridadeServico;

@Controller
@RequestMapping("/recomendacao")
public class RecomendacaoControle extends SimilaridadeServico {

	@Autowired
	private FilmeRepositorio filmeRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@GetMapping("/{usuarioId}")
	public ResponseEntity<List<RecomendacaoModelo>> recomendacao(@PathVariable Long usuarioId) {

		List<Usuario> usuarios = usuarioRepositorio.findAll();
		Optional<Usuario> usuarioExistente = usuarioRepositorio.findById(usuarioId);

		if (usuarioExistente.isPresent()) {

			Usuario usuario1 = usuarioExistente.get();

			List<FilmeResumoModelo> listaFilmesUsuario1 = buscarFilmes(usuario1.getUsuarioFilmes());
			List<RecomendacaoModelo> recomendacao = new ArrayList<>();

			for (Usuario usuario2 : usuarios) {
				List<FilmeResumoModelo> listaFilmesUsuario2 = buscarFilmes(usuario2.getUsuarioFilmes());

				Double similaridade = getEuclidiana(usuario1, usuario2);

				for (FilmeResumoModelo filme : listaFilmesUsuario1) {
					if (!listaFilmesUsuario2.contains(filme)) {
						Double nota = similaridade * filme.getNota();
						RecomendacaoModelo recomendacaoFilme = new RecomendacaoModelo(usuario2.getNome(),
								filme.getFilme().getNome(), nota);

						recomendacao.add(recomendacaoFilme);
					}
				}

			}
			return ResponseEntity.ok(recomendacao);
		}

		return ResponseEntity.notFound().build();
	}

}
