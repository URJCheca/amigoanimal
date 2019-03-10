package com.dad.amigoanimal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/*@OneToMany(mappedBy="usuario")
	private List <Mascota>  mascotas;*/
	@ManyToOne
	protected Clinica  clinica;
	protected String login;
	protected String name;
	protected String email;
	protected String document;
	protected String contrasena;
	//private List<String[]> tarjetas;
	//private int puntos=0;
	protected int rol;

	
	public Usuario() {
	}
	
	public Usuario(String login,String name, String contrasena, String document, String email, int rol) {
		this.login=login;
		this.name = name;
		this.contrasena = contrasena;
		this.document = document;
		this.email = email;
		this.rol = rol;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getRol() {
		return rol;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}
	/*
	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	public void addPuntos(int puntos) {
		this.puntos+=puntos;
	}
	
	public void restPuntos(int puntos) {
		this.puntos-=puntos;
	}*/
	


}