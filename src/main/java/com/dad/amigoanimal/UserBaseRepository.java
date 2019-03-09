package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface UserBaseRepository<T extends Usuario> extends JpaRepository <Usuario,Long> {
	Optional<Usuario> findById(Long id);
	Optional<Usuario> findByLogin(String login);
	List<Usuario> findByName(String name);
	List<Usuario> findByContrasena(String contrasena);
	List<Usuario> findByDocument (String document);
	List<Usuario> findByClinica (Clinica clinica);
	List<Usuario> findByEmail (String email);
}
