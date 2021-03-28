package br.com.lima.api.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lima.api.modelo.FilmeResumoModelo;
import br.com.lima.api.modelo.SimilaridadeOutputModelo;
import br.com.lima.dominio.modelo.Filme;
import br.com.lima.dominio.modelo.Usuario;
import br.com.lima.dominio.modelo.UsuarioFilme;
import br.com.lima.dominio.repositorio.UsuarioRepositorio;

@Controller
@RequestMapping("/similaridade")
public class SimilaridadeControle {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@GetMapping("/{usuarioId}")
	public ResponseEntity<List<SimilaridadeOutputModelo>> listar(@PathVariable Long usuarioId) {
		Optional<Usuario> usuarioExistente1 = usuarioRepositorio.findById(usuarioId);
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		List<SimilaridadeOutputModelo> similaridadeUsuarios = new ArrayList<>();

		if (usuarioExistente1.isPresent()) {
			Usuario usuario1 = usuarioExistente1.get();
			List<FilmeResumoModelo> filmeResumoModelos1 = buscarFilmes(usuario1.getUsuarioFilmes());

			for (Usuario usuario2 : usuarios) {
				List<FilmeResumoModelo> filmeResumoModelos2 = buscarFilmes(usuario2.getUsuarioFilmes());
				double euclidiana = getSimilaridade(filmeResumoModelos1, filmeResumoModelos2);
				
				SimilaridadeOutputModelo similaridadeOutputModelo = new SimilaridadeOutputModelo(usuario2.getId(), usuario2.getNome(), euclidiana);
				similaridadeUsuarios.add(similaridadeOutputModelo);
			}

			return ResponseEntity.ok(similaridadeUsuarios);
		}

		return ResponseEntity.notFound().build();
	}

	private List<FilmeResumoModelo> buscarFilmes(Set<UsuarioFilme> usuarioFilmes) {
		List<FilmeResumoModelo> filmes = new ArrayList<>();

		for (UsuarioFilme usuarioFilme : usuarioFilmes) {
			FilmeResumoModelo filmeResumoModelo = new FilmeResumoModelo();
			filmeResumoModelo.setNota(usuarioFilme.getNota());
			filmeResumoModelo.setFilme(usuarioFilme.getFilme());

			filmes.add(filmeResumoModelo);
		}

		return filmes;
	}

	private double getSimilaridade(List<FilmeResumoModelo> user1, List<FilmeResumoModelo> user2) {
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
}
