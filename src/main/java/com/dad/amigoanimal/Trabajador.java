package com.dad.amigoanimal;

import javax.persistence.ManyToOne;

public class Trabajador extends Usuario{


	public Trabajador() {
		super();
	}

	public Trabajador(String login,String name, String contrasena, String document, String email,String rol) {
		super(login,name, contrasena, document, email,rol);
	}

}
