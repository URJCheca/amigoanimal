package com.dad.amigoanimal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Mascota {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Cliente usuario;
	@ManyToMany
	private List<Clinica> clinica;
	private String name;
	private String especie;
	private String raza;
	private String color;
	private String registro;

	
	public Mascota() {}
	public Mascota(/*Usuario owner,*/ String name, String especie, String raza, String color/*Clinica clinica*/) {
		/*this.owner = owner;*/
		this.name = name;
		this.especie = especie;
		this.raza = raza;
		this.color = color;
		this.registro="";
		this.clinica= new ArrayList<Clinica>();
		/*this.clinica=clinica;*/
		
	}
	
	public Cliente getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Cliente usuario) {
		this.usuario = usuario;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEspecie() {
		return especie;
	}
	
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	public String getRaza() {
		return raza;
	}
	
	public void setRaza(String raza) {
		this.raza = raza;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	public List<Clinica> getClinica() {
		return clinica;
	}
	public void setClinica(Clinica clinica) {
		this.clinica.add(clinica);
	}
	
	public String getRegistro() {
		return registro;
	}
	
	public void setRegistro(String registro) {
		this.registro = registro;
	}
}
