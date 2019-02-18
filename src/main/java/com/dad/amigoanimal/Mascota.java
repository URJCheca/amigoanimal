package com.dad.amigoanimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Mascota {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Usuario owner;
	private String name;
	private String especie;
	private String raza;
	private String color;
	@ManyToOne
	private Clinica clinica;
	
	
	public Mascota(Usuario owner, String name, String especie, String raza, String color, String id) {
		this.owner = owner;
		this.name = name;
		this.especie = especie;
		this.raza = raza;
		this.color = color;
		
	}
	
	public Usuario getOwner() {
		return owner;
	}
	
	public void setOwner(Usuario owner) {
		this.owner = owner;
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
	
}
