package br.com.lima.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lima.api.entities.Film;
import br.com.lima.api.entities.FilmUser;
import br.com.lima.api.entities.User;
import br.com.lima.api.repositories.FilmRepository;
import br.com.lima.api.repositories.FilmUserRepository;
import br.com.lima.api.repositories.UserRepository;
import br.com.lima.model.FilmeResumoModelo;
import br.com.lima.model.SimilaridadeOutputModelo;

@Service
public class SimilarityService {

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FilmUserRepository filmUserRepository;

	public List<FilmeResumoModelo> buscarFilmes(Set<FilmUser> usuarioFilmes) {
		List<FilmeResumoModelo> filmes = new ArrayList<>();

		for (FilmUser usuarioFilme : usuarioFilmes) {
			FilmeResumoModelo filmeResumoModelo = new FilmeResumoModelo();
			filmeResumoModelo.setNote(usuarioFilme.getNote());
			filmeResumoModelo.setNote(usuarioFilme.getNote());
			filmeResumoModelo.setFilm(usuarioFilme.getFilm());

			filmes.add(filmeResumoModelo);
		}

		return filmes;
	}

	public double getEuclidiana(User usuario1, User usuario2) {
		List<FilmeResumoModelo> listaFilmesUsuario1 = buscarFilmes(usuario1.getFilms());
		List<FilmeResumoModelo> listaFilmesUsuario2 = buscarFilmes(usuario2.getFilms());

		return getSimilaridade(listaFilmesUsuario1, listaFilmesUsuario2);

	}

	public double getSimilaridade(List<FilmeResumoModelo> user1, List<FilmeResumoModelo> user2) {
		double soma = 0;
		int verificacao = 0;
		for (FilmeResumoModelo filmeResumo1 : user1) {
			Film filme1 = filmeResumo1.getFilme();
			Double nota1 = filmeResumo1.getNote();

			for (FilmeResumoModelo filmeResumo2 : user2) {
				Film filme2 = filmeResumo2.getFilme();
				Double nota2 = filmeResumo2.getNote();

				if (filme1.equals(filme2)) {
					soma += Math.pow((nota1 - nota2), 2);
					verificacao = 1;
				}
			}
		}

		if (verificacao == 0) {
			return 0;
		}

		return (1 / (1 + Math.sqrt(soma)));
	}

	public List<SimilaridadeOutputModelo> getSimilaridadeGeral(User user, List<User> users) {

		List<SimilaridadeOutputModelo> similaridadeUsuarios = new ArrayList<>();
		List<FilmeResumoModelo> filmeResumoModelos1 = buscarFilmes(user.getFilms());

		for (User user2 : users) {
			List<FilmeResumoModelo> filmeResumoModelos2 = buscarFilmes(user2.getFilms());
			double euclidiana = getSimilaridade(filmeResumoModelos1, filmeResumoModelos2);

			SimilaridadeOutputModelo similaridadeOutputModelo = new SimilaridadeOutputModelo(user2.getId(),
					user2.getName(), euclidiana);
			similaridadeUsuarios.add(similaridadeOutputModelo);
		}

		return similaridadeUsuarios;
	}
}
