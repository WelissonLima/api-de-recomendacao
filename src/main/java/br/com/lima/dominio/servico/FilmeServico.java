package br.com.lima.dominio.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lima.dominio.modelo.Filme;
import br.com.lima.dominio.repositorio.FilmeRepositorio;

@Service
public class FilmeServico {

	@Autowired
	FilmeRepositorio repositorio;

	public List<Filme> findAll() {
		return repositorio.findAll();
	}

	public Filme findById(Long id) {
		Optional<Filme> film = repositorio.findById(id);
		return film.orElseThrow();
	}

	public Filme create(Filme filme) {
		return repositorio.save(new Filme(null, filme.getNome(), filme.getSinopse()));
			
	}

	public void delete(Long id) {
		repositorio.deleteById(id);
	}

	public Filme update(Long id, Filme obj) {
		Filme entidade = repositorio.getOne(id);
		updateDate(entidade, obj);
		return repositorio.save(entidade);

	}

	private void updateDate(Filme entidade, Filme obj) {
		entidade.setNome(obj.getNome());
	}
}
