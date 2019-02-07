package com.dad.amigoanimal;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Clinica {

	@OneToMany
	private  Usuario[]  clientes;
	@OneToMany
	private Mascota[] mascotas;
	private int salas;
	public Clinica(Usuario clientes, Mascota[] mascotas, int salas) {
		this.clientes = clientes;
		this.mascotas = mascotas;
		this.salas = salas;
	}
	public Usuario[] getClientes() {
		return clientes;
	}
	public void setClientes(Usuario[] clientes) {
		this.clientes = clientes;
	}
	public Mascota[] getMascotas() {
		return mascotas;
	}
	public void setMascotas(Mascota[] mascotas) {
		this.mascotas = mascotas;
	}
	public int getSalas() {
		return salas;
	}
	public void setSalas(int salas) {
		this.salas = salas;
	}
	
}
