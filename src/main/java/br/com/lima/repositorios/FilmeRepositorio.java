package br.com.lima.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.domains.Filme;

public interface FilmeRepositorio extends JpaRepository<Filme, Long>{

	Filme findByNome(String nome);

}
