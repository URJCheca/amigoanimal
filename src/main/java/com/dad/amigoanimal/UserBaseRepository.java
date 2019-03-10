package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface UserBaseRepository<T extends Usuario> extends JpaRepository <Usuario,Long> {
	
	Optional<Usuario> findByLogin(String login);
	Page<Usuario> findByName(String name, Pageable page);
	Page<Usuario> findByContrasena(String contrasena, Pageable page);
	Usuario findByDocument (String document);
	Page<Usuario> findByClinica (Clinica clinica, Pageable page);
	Page<Usuario> findByEmail (String email, Pageable page);
}
