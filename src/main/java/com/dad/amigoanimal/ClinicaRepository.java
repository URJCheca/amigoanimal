package com.dad.amigoanimal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicaRepository extends JpaRepository <Clinica, Long>{

	List<Clinica> findAll();
	
	
/*	public default void saveCliente (Usuario cliente) {
		
		Clinica clinica = this.findAll().get(1);
		clinica.setCliente(cliente);
		this.deleteAll();
		this.save(clinica);
	}
	
	public default void deleteCliente (Usuario cliente) {
		
		Clinica clinica = this.findAll().get(1);
		clinica.getClientes().remove(cliente);
		this.deleteAll();
		this.save(clinica);
	}
	*/
	public default void saveMascota (Mascota mascota) {
		
		Clinica clinica = this.findAll().get(1);
		clinica.setMascota(mascota);
		this.deleteAll();
		this.save(clinica);
	}
	
	public default void deleteMascota (Mascota mascota) {
		
		Clinica clinica = this.findAll().get(1);
		clinica.getMascotas().remove(mascota);
		this.deleteAll();
		this.save(clinica);
	}
	
}
