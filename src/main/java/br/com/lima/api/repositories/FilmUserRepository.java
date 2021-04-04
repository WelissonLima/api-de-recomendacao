package br.com.lima.api.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lima.api.entities.FilmUser;

@Repository
public interface FilmUserRepository extends JpaRepository<FilmUser, Long> {

}
