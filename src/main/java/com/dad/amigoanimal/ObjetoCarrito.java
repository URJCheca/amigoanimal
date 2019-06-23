package com.dad.amigoanimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set; 
//HAy que ver como crear un carrito por cliente y usarlo en los controladores
public class ObjetoCarrito {
	private long id;
	private String nombreProducto;
	private int cantidad;

	
	public ObjetoCarrito(Long id,String nombre, int cantidad) {
		this.id=id;
		this.nombreProducto=nombre;
		this.cantidad=cantidad;
	}


	public String getNombreProducto() {
		return nombreProducto;
	}


	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	

}
