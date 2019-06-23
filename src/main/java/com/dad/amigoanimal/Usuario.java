 package com.dad.amigoanimal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Inheritance
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/*@OneToMany(mappedBy="usuario")
	private List <Mascota>  mascotas;*/
	/*@ManyToMany
	protected List<Clinica> clinica;*/
	protected String login;
	protected String name;
	protected String email;
	protected String document;
	protected String contrasena;
	//private List<String[]> tarjetas;
	//private int puntos=0;
	
	private String passwordHash;
	private String rol;
	
	//@ElementCollection(fetch = FetchType.EAGER)
	//private List<String> roles;

	
	public Usuario() {
	}
	
	public Usuario(String login,String name, String contrasena, String document, String email, String rol) {
		this.login=login;
		this.name = name;
		this.contrasena = contrasena;
		this.setPasswordHash(contrasena);
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
	
	public String getcontrasena() {
		return contrasena;
	}
	public String getRol() {
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String password) {
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
	}

	/*public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}*/
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