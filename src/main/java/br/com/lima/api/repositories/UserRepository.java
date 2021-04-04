package br.com.lima.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lima.api.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

}
