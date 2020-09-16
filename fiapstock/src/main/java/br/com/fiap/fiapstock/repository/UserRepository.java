package br.com.fiap.fiapstock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fiapstock.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findFirstByUsername(String username);
	
}
