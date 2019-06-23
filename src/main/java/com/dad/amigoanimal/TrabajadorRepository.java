package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

	
@Transactional
public interface TrabajadorRepository extends UserBaseRepository {
	Optional<Trabajador> findByName (String name);
	//Optional<Cliente> findById(Long id);
	Optional<Trabajador> findByLogin(String login);
	//List<Cliente> findByName(String name);
	//List<Cliente> findByContrasena(String contrasena);
	//Cliente findByDocument (String document);
	List<Trabajador> findByClinica (Clinica clinica);
	//List<Cliente> findByEmail (String email);
}
