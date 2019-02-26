package com.dad.amigoanimal;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/*@OneToMany
	private List<Mascota>  mascotas;*/
	
	private String name;
	private String email;
	private String document;
	private String contrasena;
	private int rol;

	
	public Usuario() {
	}
	
	public Usuario(String name, String contrasena, String document, String email, int rol) {
		this.name = name;
		this.contrasena = contrasena;
		this.document = document;
		this.email = email;
		this.rol = rol;
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


}