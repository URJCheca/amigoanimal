package com.dad.amigoanimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set; 
//HAy que ver como crear un carrito por cliente y usarlo en los controladores
public class Carrito {

	private List<Producto> productos;
	private float precioTotal;

	private  HashMap<Producto, Integer> cosas = new HashMap<>(); 
	public Carrito() {
		precioTotal = 0;
	}

	public void addProducto(Producto producto, int quantity) {
		if (cosas.containsKey(producto)) {
			cosas.put(producto, cosas.get(producto) + quantity);
		} else {
			cosas.put(producto, quantity);
		}
		System.out.println(precioTotal);
		precioTotal=precioTotal+ producto.getPrice()*quantity;
		System.out.println(precioTotal);
	}
	
	public void removeProducto(Producto producto, int quantity) {
		if (cosas.containsKey(producto)) {
			cosas.put(producto, cosas.get(producto) - quantity);
			precioTotal-= producto.getPrice()* quantity;
			if (cosas.get(producto)<=0)
				cosas.remove(producto);
		}
	}
	
	public boolean esta(Producto producto) {
		return cosas.containsKey(producto);
	}
	public int getQuantity(Producto producto) {
		return cosas.get(producto);
	}
	
	public float getPrecioTotal() {
		return precioTotal;
	}

	public Set<Entry<Producto, Integer>> getCosas() {
		return cosas.entrySet();
		
	}

	public void setCosas(HashMap<Producto, Integer> cosas) {
		this.cosas = cosas;
	}

}
