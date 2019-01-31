package com.dad.amigoanimal;

public class carrito {

	private String[] productos; //hashmap, lista de productos?
	//List<String> productos = new ArrayList<String>();
	private float precioTotal;

	public carrito() {
		productos = null;
		precioTotal = 0;
	}

	public void addProducto(String producto, int quantity) {
		//productos.add(producto);
		//precioTotal += producto.price;
	}
	public void removeProducto(int quantity) {
		
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

}
