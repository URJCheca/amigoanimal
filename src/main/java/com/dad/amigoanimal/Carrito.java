package com.dad.amigoanimal;

import java.util.HashMap; 
import java.util.Map; 

public class Carrito {

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
		// precioTotal += producto.price * quantity;
		//cosas.forEach((k,v) -> precioTotal+= k.price * v;
	}
	public void removeProducto(Producto producto, int quantity) {
		if (cosas.containsKey(producto)) {
			cosas.put(producto, cosas.get(producto) - quantity);
			if (cosas.get(producto)<=0)
				cosas.remove(producto);
		}
	}

	public float getPrecioTotal() {
		precioTotal = 0;
		cosas.forEach((k,v) -> precioTotal+= k.getPrice() * v);
		return precioTotal;
	}
}
