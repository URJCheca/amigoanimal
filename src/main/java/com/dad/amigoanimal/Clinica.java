package com.dad.amigoanimal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Clinica {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToMany(mappedBy="clinica")
	private List <Usuario>  clientes;
	@OneToMany(mappedBy="clinica")
	private List<Mascota> mascotas;
	private int salas;
	public Clinica() {}
	public Clinica(String nombre,int salas) {
		this.setName(nombre);
		/*this.clientes = new ArrayList<>();*/
		this.mascotas = new ArrayList<>();
		this.salas = salas;
	}
	public List<Usuario> getClientes() {
		return clientes;
	}
	public void setClientes(Usuario cliente) {
		this.clientes.add(cliente);
	}
	public List<Mascota> getMascotas() {
		return mascotas;
	}
	public void setMascota(Mascota mascota) {
		this.mascotas.add(mascota);
	}
	public int getSalas() {
		return salas;
	}
	public void setSalas(int salas) {
		this.salas = salas;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
