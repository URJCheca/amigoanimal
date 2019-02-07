package com.dad.amigoanimal;

public class Clinica {


	private  Usuario[]  clientes;
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
