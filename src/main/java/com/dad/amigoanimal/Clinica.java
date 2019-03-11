package com.dad.amigoanimal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Clinica {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private int salas;
	
	@ManyToMany(mappedBy="clinica")
	private List <Cliente>  clientes;
	/*@OneToMany(mappedBy="clinica")
	private List <Trabajador>  trabajadores;*/
	@ManyToMany(mappedBy="clinica")
	private List<Mascota> mascotas;
		
	
	public Clinica() {}
	public Clinica(String nombre,int salas) {
		this.setName(nombre);
		/*this.clientes = new ArrayList<>();*/
		this.mascotas = new ArrayList<>();
		this.salas = salas;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(Cliente cliente) {
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
