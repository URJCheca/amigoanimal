package com.dad.amigoanimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
//HAy que ver como crear un carrito por cliente y usarlo en los controladores
public class Carrito {

	private List<Producto> productos;
	private float precioTotal;

	HashMap<Producto, Integer> cosas = new HashMap<>(); 
	public Carrito() {
		precioTotal = 0;
	}

	public void addProducto(Producto producto, int quantity) {
		if (cosas.containsKey(producto)) {
			cosas.put(producto, cosas.get(producto) + quantity);
		} else {
			cosas.put(producto, quantity);
		}
		precioTotal+= producto.getPrice()*quantity;
	}
	public void removeProducto(Producto producto, int quantity) {
		if (cosas.containsKey(producto)) {
			cosas.put(producto, cosas.get(producto) - quantity);
			precioTotal-= producto.getPrice()* quantity;
			if (cosas.get(producto)<=0)
				cosas.remove(producto);
		}
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

}
