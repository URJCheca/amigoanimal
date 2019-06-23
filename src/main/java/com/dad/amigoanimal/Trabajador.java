package com.dad.amigoanimal;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Trabajador extends Usuario{
	
	@ManyToOne
	private Clinica clinica;
	
	public Trabajador() {
		super();
	}

	public Trabajador(String login,String name, String contrasena, String document, String email,String rol) {
		super(login,name, contrasena, document, email,rol);
	}
	public Clinica getClinica() {
		return clinica;
}

	public void setClinica(Clinica clinica) {
	this.clinica=clinica;
}
}
