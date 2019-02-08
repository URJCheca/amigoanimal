package com.dad.amigoanimal;

import java.util.HashMap; 
import java.util.Map; 

public class Carrito {

	private Producto[] productos;
	private float precioTotal;

	HashMap<Producto, Integer> cosas = new HashMap<>(); 
	public Carrito() {
		productos = null;
		precioTotal = 0;
	}

	public void addProducto(Producto producto, int quantity) {
		if (cosas.containsKey(producto)) {
			cosas.put(producto, cosas.get(producto) + quantity);
		} else {
			cosas.put(producto, quantity);
		}
		cosas.forEach((k,v) -> precioTotal+= k.price * v;
	}
	public void removeProducto(Producto producto, int quantity) {
		if (cosas.containsKey(producto)) {
			cosas.put(producto, cosas.get(producto) - quantity);
			if (cosas.get(producto)<=0)
				cosas.remove(producto);
		}
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

}
