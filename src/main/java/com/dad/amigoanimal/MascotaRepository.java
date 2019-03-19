package com.dad.amigoanimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MascotaRepository extends JpaRepository <Mascota, Long>{

	Page<Mascota> findByName(String name, Pageable page);
	Page<Mascota> findByUsuario (Usuario usuario, Pageable page);
	Page<Mascota> findByEspecie (String especie, Pageable page);
	Page<Mascota> findByClinica (Clinica clinica, Pageable page);
	
	Page<Mascota> findByNameAndUsuario(String name, Usuario usuario, Pageable page);
	Page<Mascota> findByNameAndEspecie (String name,String especie, Pageable page);
	Page<Mascota> findByNameAndClinica (String name,Clinica clinica, Pageable page);
	Page<Mascota> findByUsuarioAndEspecie (Usuario usuario,String especie, Pageable page);
	Page<Mascota> findByUsuarioAndClinica (Usuario usuario, Clinica clinica, Pageable page);
	Page<Mascota> findByEspecieAndClinica (String especie,Clinica clinica, Pageable page);
	
	Page<Mascota> findByNameAndUsuarioAndEspecie(String name,Usuario usuario,String especie, Pageable page);
	Page<Mascota> findByNameAndUsuarioAndClinica(String name, Usuario usuario, Clinica clinica, Pageable page);
	Page<Mascota> findByNameAndEspecieAndClinica (String name, String especie,Clinica clinica, Pageable page);
	Page<Mascota> findByUsuarioAndEspecieAndClinica (Usuario usuario,String especie,Clinica clinica, Pageable page);
	
	Page<Mascota> findByNameAndUsuarioAndEspecieAndClinica(String name,Usuario usuario,String especie,Clinica clinica, Pageable page);

}
