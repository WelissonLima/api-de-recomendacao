package br.com.lima.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.entities.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);

}
