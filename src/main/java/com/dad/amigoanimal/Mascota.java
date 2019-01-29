package com.dad.amigoanimal;

public class Mascota {
	
	private String owner;
	private String name;
	private String especie;
	private String raza;
	private String color;
	private String id;
	
	public Mascota(String owner, String name, String especie, String raza, String color, String id) {
		this.owner = owner;
		this.name = name;
		this.especie = especie;
		this.raza = raza;
		this.color = color;
		this.id = id;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
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
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
