package br.com.lima.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.domains.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);

}
