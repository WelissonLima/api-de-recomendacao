package br.com.lima.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.api.domains.UsuarioFilme;

public interface UsuarioFilmeRepositorio extends JpaRepository<UsuarioFilme, Long> {

}
