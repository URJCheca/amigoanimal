package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

	
@Transactional
public interface ClienteRepository extends UserBaseRepository {
	Cliente findByName (String name);
	//Optional<Cliente> findById(Long id);
	//Optional<Cliente> findByLogin(String login);
	//List<Cliente> findByName(String name);
	//List<Cliente> findByContrasena(String contrasena);
	//Cliente findByDocument (String document);
	List<Cliente> findByClinica (Clinica clinica);
	//List<Cliente> findByEmail (String email);
}
