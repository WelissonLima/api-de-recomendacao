package br.com.lima.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lima.api.entities.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

	Film findByName(String name);

}

