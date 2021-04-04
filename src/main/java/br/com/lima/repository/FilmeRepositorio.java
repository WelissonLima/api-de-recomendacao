package br.com.lima.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.entities.Filme;

public interface FilmeRepositorio extends JpaRepository<Filme, Long>{

	Filme findByNome(String nome);

}
