package br.com.lima.api.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lima.api.entities.User;
import br.com.lima.api.repositories.FilmRepository;
import br.com.lima.api.repositories.UserRepository;
import br.com.lima.api.services.SimilarityService;
import br.com.lima.model.FilmeResumoModelo;
import br.com.lima.model.RecomendacaoModelo;

@Controller
@RequestMapping("/recomendation")
public class RecomendationResource extends SimilarityService {

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/{usuarioId}")
	public ResponseEntity<List<RecomendacaoModelo>> recomendacao(@PathVariable Long usuarioId) {

		List<User> usuarios = userRepository.findAll();
		Optional<User> usuarioExistente = userRepository.findById(usuarioId);

		if (usuarioExistente.isPresent()) {

			User usuario1 = usuarioExistente.get();

			List<FilmeResumoModelo> listaFilmesUsuario1 = buscarFilmes(usuario1.getFilms());
			List<RecomendacaoModelo> recomendacao = new ArrayList<>();

			for (User usuario2 : usuarios) {
				List<FilmeResumoModelo> listaFilmesUsuario2 = buscarFilmes(usuario2.getFilms());

				Double similaridade = getEuclidiana(usuario1, usuario2);

				for (FilmeResumoModelo filme : listaFilmesUsuario1) {
					if (!listaFilmesUsuario2.contains(filme)) {
						Double nota = similaridade * filme.getNote();
						RecomendacaoModelo recomendacaoFilme = new RecomendacaoModelo(usuario2.getName(),
								filme.getFilme().getName(), nota);

						recomendacao.add(recomendacaoFilme);
					}
				}

			}
			return ResponseEntity.ok(recomendacao);
		}

		return ResponseEntity.notFound().build();
	}

}
