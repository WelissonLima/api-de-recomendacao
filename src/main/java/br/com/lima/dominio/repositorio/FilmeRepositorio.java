package br.com.lima.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.dominio.modelo.Filme;

public interface FilmeRepositorio extends JpaRepository<Filme, Long>{

	Filme findByNome(String nome);

}
