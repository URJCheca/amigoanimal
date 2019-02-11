package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
	
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
	Optional<Usuario> findById(Long id);
	List<Usuario> findByName(String name);
	List<Usuario> findByDocument (String email);
	List<Usuario> findByEmail (String document);
}
