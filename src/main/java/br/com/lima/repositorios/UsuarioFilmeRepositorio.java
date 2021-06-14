package br.com.lima.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.domains.UsuarioFilme;

public interface UsuarioFilmeRepositorio extends JpaRepository<UsuarioFilme, Long> {

}
