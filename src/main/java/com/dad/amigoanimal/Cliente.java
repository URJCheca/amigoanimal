package com.dad.amigoanimal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Usuario {
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;*/
	@OneToMany(mappedBy="usuario")
	private List <Mascota>  mascotas;
	@ManyToMany
	protected List<Clinica> clinica;
	/*@ManyToOne
	private Clinica clinica;*/
	private int puntos=0;
	
	public Cliente() {
		super();
	}
	
	public Cliente(String login,String name, String contrasena, String document, String email, int rol) {
		super(login,name,contrasena,document,email,rol);
		mascotas= new ArrayList<Mascota>();
		clinica= new ArrayList<Clinica>();
		
	}
	
	 public void addMascota(Mascota mascota) {
		 mascotas.add(mascota);
	 } 
	 
	 public List<Clinica> getClinica() {
			return clinica;
		}

		public void setClinica(Clinica clinica) {
			this.clinica.add(clinica);
		}
	/*public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}*/
}