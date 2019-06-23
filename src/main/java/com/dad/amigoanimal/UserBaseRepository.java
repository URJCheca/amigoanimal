package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.dad.amigoanimal.Usuario;


@NoRepositoryBean
public interface UserBaseRepository<T extends Usuario> extends JpaRepository <Usuario,Long> {
	
	Optional<Usuario> findByName(String name);
	//Cliente findByName(String name);
	Optional<Usuario> findByLogin(String login);
	//List<Usuario> findByName(String name/*, Pageable page*/);
	Page<Usuario> findByContrasena(String contrasena, Pageable page);
	Optional<Usuario> findByDocument (String document);
	//List<Usuario> findByClinica (Clinica clinica/*, Pageable page*/);
	Page<Usuario> findByEmail (String email, Pageable page);
}
