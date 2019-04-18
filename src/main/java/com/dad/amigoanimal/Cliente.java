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
	private  HashMap<Producto, Integer> carrito = new HashMap<>(); 
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
	
	public void addProducto(Producto producto, int quantity) {
		encontrado = false;
		carrito.forEach((k,v) -> {
			System.out.println(k.getName() + " " + producto.getName());
			if (k.getName() == producto.getName()){
				carrito.put(k, v + quantity);
				System.out.println("si");
				encontrado=true;
			}
		});
		if (!encontrado) {
			carrito.put(producto, quantity);
			System.out.println("no");
		}
		System.out.println("añadido");
		System.out.println(this.getLista());
		System.out.println(carrito.size());
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
		carrito.forEach((k,v) -> lista += (v + " de " + k.getName() + "\n"));
		return lista;
	}
	
}
