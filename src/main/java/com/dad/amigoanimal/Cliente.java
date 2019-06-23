package com.dad.amigoanimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

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
	private static HashMap<Long, Integer> carrito; 
	//private  HashMap<String, Integer> carritoS; 
	private int puntos=0;

	public Cliente() {
		super();
	}
	
	public Cliente(String login,String name, String contrasena, String document, String email, String rol) {
		super(login,name,contrasena,document,email,rol);
		mascotas= new ArrayList<Mascota>();
		clinica= new ArrayList<Clinica>();
		carrito = new  HashMap<Long, Integer>();
		
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
	
	
	public void addProducto2(Long id, int quantity) {
		if (carrito.containsKey(id)) {
			carrito.put(id, carrito.get(id) + quantity);
		}else {
			carrito.put(id, quantity);
		}
		
	
		/*System.out.println("añadido");
		System.out.println(carrito.size());
		System.out.println(this.getLista());*/
	}
	
	public void setMap(HashMap<Long, Integer> carro) {
		System.out.println("--" + carro.size());
		System.out.println("---" + carrito.size());
		this.carrito = carro;
		System.out.println("----" + carrito.size());
	}
	
	public HashMap<Long, Integer> getMap(){
		System.out.println("-" + carrito.size());
		return this.carrito;
	}
	
	public void addProducto(Long id, int quantity) {
		if (carrito.containsKey(id)) {
			carrito.put(id, carrito.get(id) + quantity);
		}else {
			carrito.put(id, quantity);
		}
	}
	
	public void removeProducto(Long id, int quantity) {
		System.out.println(id);
		if (carrito.containsKey(id)) {
			carrito.put(id, carrito.get(id) - quantity);
			if (carrito.get(id)<=0)
				carrito.remove(id);
		}
	}
	
	public boolean esta(Producto producto) {
		return carrito.containsKey(producto);
	}
	public int getQuantity(Producto producto) {
		return carrito.get(producto);
	}
	
	/*public float getPrecioTotal() {
		preciototal = 0;
		//carrito.forEach((k,v) -> System.out.println("Producto: "+k+" cantidad:"+v));
		carrito.forEach((k,v) -> preciototal += k.getPrice() * v);
		return preciototal;
	}
	
	public List<ObjetoCarrito> getLista() {
		List <ObjetoCarrito>lista = new ArrayList();
		Set<Entry<Long, Integer>> contenido = carrito.entrySet();
		for(Entry<Long, Integer> entrada:contenido) {
			lista.add(new ObjetoCarrito(entrada.getKey().,entrada.getKey().getName(),entrada.getValue()));
		} 
		return lista;
//		carrito.forEach((k,v) -> lista += (v + " de " + k.getName() + ""));
//		return lista;
	}*/

	
}
