package com.dad.amigoanimal;

import java.util.ArrayList;
import java.util.HashMap;
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
	private static HashMap<Producto, Integer> carrito; 
	//private  HashMap<String, Integer> carritoS; 
	private int puntos=0;
	private int preciototal;
	private String lista;
	private boolean encontrado;
	public Cliente() {
		super();
	}
	
	public Cliente(String login,String name, String contrasena, String document, String email, String rol) {
		super(login,name,contrasena,document,email,rol);
		mascotas= new ArrayList<Mascota>();
		clinica= new ArrayList<Clinica>();
		carrito = new  HashMap<Producto, Integer>();
		
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
	
	
	public HashMap<Producto, Integer> addProducto2(Producto producto, int quantity, HashMap<Producto,Integer> carro) {
		System.out.println("x" + carrito.size());
		encontrado = false;
		carro.forEach((k,v) -> {
			//System.out.println(k.getName() + " " + producto.getName());
			if (k.equals(producto)){
				System.out.println("++++" + carro.size());
				carro.put(k, v + quantity);
				System.out.println("+++++" + carro.size());
				encontrado=true;
			}
		});
		if (!encontrado) {
			//System.out.println(this.getLista());
			System.out.println("++++" + carro.size());
			carro.put(producto, quantity);
			System.out.println("+++++" + carro.size());
		}
		System.out.println("++++++" + carro.size());
		return carro;
		/*System.out.println("añadido");
		System.out.println(carrito.size());
		System.out.println(this.getLista());*/
	}
	
	public void setMap(HashMap<Producto, Integer> carro) {
		System.out.println("--" + carro.size());
		System.out.println("---" + carrito.size());
		this.carrito = carro;
		System.out.println("----" + carrito.size());
	}
	
	public HashMap<Producto, Integer> getMap(){
		System.out.println("-" + carrito.size());
		return this.carrito;
	}
	
	public void addProducto(Producto producto, int quantity) {
		encontrado = false;

		carrito.forEach((k,v) -> {
			//System.out.println(k.getName() + " " + producto.getName());
			if (k.equals(producto)){
				System.out.println(carrito.size());
				carrito.put(k, v + quantity);
				System.out.println(carrito.size());
				encontrado=true;
			}
		});
		if (!encontrado) {
			System.out.println(this.getLista());
			carrito.put(producto, quantity);
		}
		/*System.out.println("añadido");
		System.out.println(carrito.size());
		System.out.println(this.getLista());*/
	}
	
	public void removeProducto(Producto producto, int quantity) {
		if (carrito.containsKey(producto)) {
			carrito.put(producto, carrito.get(producto) - quantity);
			if (carrito.get(producto)<=0)
				carrito.remove(producto);
		}
	}
	
	public boolean esta(Producto producto) {
		return carrito.containsKey(producto);
	}
	public int getQuantity(Producto producto) {
		return carrito.get(producto);
	}
	
	public float getPrecioTotal() {
		preciototal = 0;
		//carrito.forEach((k,v) -> System.out.println("Producto: "+k+" cantidad:"+v));
		carrito.forEach((k,v) -> preciototal += k.getPrice() * v);
		return preciototal;
	}
	
	public String getLista() {
		lista = "";
		carrito.forEach((k,v) -> lista += (v + " de " + k.getName() + ""));
		return lista;
	}

	
}
