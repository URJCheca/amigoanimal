package com.dad.amigoanimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MascotaRepository extends JpaRepository <Mascota, Long>{

	Page<Mascota> findByName(String name, Pageable page);
	Page<Mascota> findByUsuario (Usuario usuario, Pageable page);
	Page<Mascota> findByEspecie (String especie, Pageable page);
	Page<Mascota> findByNameAndUsuario (String name, Usuario usuario, Pageable page);
		
}
