package br.com.lima.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.api.domains.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);

}
