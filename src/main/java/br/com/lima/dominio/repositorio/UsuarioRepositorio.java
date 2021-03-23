package br.com.lima.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.dominio.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);

}
