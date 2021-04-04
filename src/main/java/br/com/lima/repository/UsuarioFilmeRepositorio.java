package br.com.lima.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.entities.UsuarioFilme;

public interface UsuarioFilmeRepositorio extends JpaRepository<UsuarioFilme, Long> {

}
