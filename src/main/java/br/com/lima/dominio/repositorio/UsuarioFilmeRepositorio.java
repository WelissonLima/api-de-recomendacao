package br.com.lima.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.dominio.modelo.UsuarioFilme;

public interface UsuarioFilmeRepositorio extends JpaRepository<UsuarioFilme, Long> {

}
