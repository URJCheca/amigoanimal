package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface MascotaRepository extends JpaRepository <Mascota, Long>{
	Optional<Mascota> findById(Long id);
	List<Mascota> findByName(String name);
	List<Mascota> findByOwner (Usuario owner);
	List<Mascota> findByEspecie (String especie);
	
		
}
