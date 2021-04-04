package br.com.lima.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lima.entities.Filme;
import br.com.lima.entities.Usuario;
import br.com.lima.entities.UsuarioFilme;
import br.com.lima.model.FilmeResumoModelo;
import br.com.lima.model.SimilaridadeOutputModelo;
import br.com.lima.repository.FilmeRepositorio;
import br.com.lima.repository.UsuarioFilmeRepositorio;
import br.com.lima.repository.UsuarioRepositorio;

@Service
public class SimilaridadeServico {

	@Autowired
	private FilmeRepositorio filmeRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private UsuarioFilmeRepositorio usuarioFilmeRepositorio;

	public List<FilmeResumoModelo> buscarFilmes(Set<UsuarioFilme> usuarioFilmes) {
		List<FilmeResumoModelo> filmes = new ArrayList<>();

		for (UsuarioFilme usuarioFilme : usuarioFilmes) {
			FilmeResumoModelo filmeResumoModelo = new FilmeResumoModelo();
			filmeResumoModelo.setNota(usuarioFilme.getNota());
			filmeResumoModelo.setFilme(usuarioFilme.getFilme());

			filmes.add(filmeResumoModelo);
		}

		return filmes;
	}

	public double getEuclidiana(Usuario usuario1, Usuario usuario2) {
		List<FilmeResumoModelo> listaFilmesUsuario1 = buscarFilmes(usuario1.getUsuarioFilmes());
		List<FilmeResumoModelo> listaFilmesUsuario2 = buscarFilmes(usuario2.getUsuarioFilmes());

		return getSimilaridade(listaFilmesUsuario1, listaFilmesUsuario2);

	}

	public double getSimilaridade(List<FilmeResumoModelo> user1, List<FilmeResumoModelo> user2) {
		double soma = 0;
		int verificacao = 0;
		for (FilmeResumoModelo filmeResumo1 : user1) {
			Filme filme1 = filmeResumo1.getFilme();
			int nota1 = filmeResumo1.getNota();

			for (FilmeResumoModelo filmeResumo2 : user2) {
				Filme filme2 = filmeResumo2.getFilme();
				int nota2 = filmeResumo2.getNota();

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

	public List<SimilaridadeOutputModelo> getSimilaridadeGeral(Usuario usuario, List<Usuario> usuarios) {

		List<SimilaridadeOutputModelo> similaridadeUsuarios = new ArrayList<>();
		List<FilmeResumoModelo> filmeResumoModelos1 = buscarFilmes(usuario.getUsuarioFilmes());

		for (Usuario usuario2 : usuarios) {
			List<FilmeResumoModelo> filmeResumoModelos2 = buscarFilmes(usuario2.getUsuarioFilmes());
			double euclidiana = getSimilaridade(filmeResumoModelos1, filmeResumoModelos2);

			SimilaridadeOutputModelo similaridadeOutputModelo = new SimilaridadeOutputModelo(usuario2.getId(),
					usuario2.getNome(), euclidiana);
			similaridadeUsuarios.add(similaridadeOutputModelo);
		}

		return similaridadeUsuarios;
	}
}
