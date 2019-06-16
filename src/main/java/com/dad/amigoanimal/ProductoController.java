package com.dad.amigoanimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.Cacheable;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepositorio;
	
	@Autowired
	private ClienteRepository userRepository;
	
	int numElem= 3;
	String nombre;
	Cliente usuario;
	private /*static*/ HashMap<Producto, Integer> carrete;
	
	@PostConstruct
	public void init() {
		productoRepositorio.save(new Producto("Catzilla", 14, "Comida para gatos de alta calidad. Sabor pollo y verduras", "Alimentacion", 30));
		productoRepositorio.save(new Producto("Transportin grande", 20, "Transportin de grandes dimensiones para perros", "Viaje", 5));
		productoRepositorio.save(new Producto("Bun bunny", 5, "Complemento alimenticio para conejos", "Alimentacion",20));
		productoRepositorio.save(new Producto("Arenero", 10, "Arenero para gatos", "Higiene", 15));
		productoRepositorio.save(new Producto("Anti-lombrices", 8, "Pastillas anti parasitarias para perros y gatos", "Salud", 50));
		productoRepositorio.save(new Producto("Soga mordedora", 8, "Juguete para perros hecho de cuerda resistente para jugar al tira y afloja", "Juguetes", 10));
		productoRepositorio.save(new Producto("Kit Correa externsible", 13, "Correa extensible hasta 4 metros con collar incluido", "Accesorios", 7));
		productoRepositorio.save(new Producto("Champu para perros", 7, "Champu especialmente diseñado para perros. Deja el pelo suave y brillante", "Higiene", 12));

		
		
	}
	@RequestMapping("/crear_carrito")
	public String crearCarrito (Model model) {
		//Carrito carrito= new Carrito();
		Page<Producto> lista = productoRepositorio.findAll(new PageRequest(0, numElem));
		model.addAttribute("productos",lista);
		//model.addAttribute("carrito",carrito);
		model.addAttribute("numPag", 0);
		
		return "catalogo_template";
	}
	@RequestMapping ("/catalogo")
	public String catalogoController (Model model, @RequestParam int numPag/*, @RequestParam Carrito carrito*/) {
		
		Page<Producto> lista = productoRepositorio.findAll(new PageRequest(numPag, numElem));
		
		model.addAttribute("productos",lista);
		//model.addAttribute("carrito",carrito);
		model.addAttribute("numPag", numPag+1);
		
		return "catalogo_template";
	}
	
	
	@RequestMapping ("/ver_carrito")
	public String  verCarrito (Model model, HttpServletRequest request) {
		
		/*Set<Entry<Producto, Integer>> cosas = carrito.getCosas();
		for (Entry<Producto, Integer> cosa : cosas) {
			   Producto key = cosa.getKey();
			   Integer value = cosa.getValue();
			  model.addAttribute("clave", key);
			  model.addAttribute("valor", value);
			} 
		*/
		nombre = request.getUserPrincipal().getName();
		usuario = userRepository.findByName(nombre);
		model.addAttribute("lista", usuario.getLista());
		model.addAttribute("precio", usuario.getPrecioTotal());
		
		return "carrito_template";
		
	}
	@RequestMapping ("/aniadir_carrito")
	public String aniadirCarrito (Model model, String id, int quantity, /*Carrito carrito,*/ HttpServletRequest request) {

		long longID=Long.parseLong(id);
		Optional<Producto> optional=productoRepositorio.findById(longID);
		Producto producto= optional.get();
		nombre = request.getUserPrincipal().getName();
		usuario = userRepository.findByName(nombre);
		carrete = usuario.getMap();
		System.out.println("+" + carrete.size());
		carrete = usuario.addProducto2(producto, quantity, carrete);
		System.out.println("++" + carrete.size());
		usuario.setMap(carrete);
		System.out.println("+++" + usuario.getMap().size());
		//carrito.addProducto(producto, quantity);
		//System.out.println("Se añadio con exito "+producto.getName()+" "+ carrito.esta(producto)+ " "+carrito.getQuantity(producto)+" "+carrito.getPrecioTotal() );
		Page<Producto> lista = productoRepositorio.findAll(new PageRequest(0, numElem));
		model.addAttribute("productos",lista);
		//model.addAttribute("carrito",carrito);
		model.addAttribute("numPag", 0);
		model.addAttribute("lista", usuario.getLista());
		return "catalogo_template";
	}
	

	@GetMapping ("/busqueda_avanzada_producto")
	public String BusquedaAvanzada (Model model,@RequestParam String nombre,@RequestParam String tipo,@RequestParam int precio,@RequestParam int numPag) {
		Page<Producto> lista;
		//Carrito carrito= new Carrito();
		
		int sigPag= numPag+1;
		int precioaux=precio+1;
		
		PageRequest pagerequest=new PageRequest(numPag, numElem);
		
		//realizar para cada una de las posibles busquedas
		
		int funcion=0;
		
		if (!nombre.equals(""))  
			funcion+=1;
					
		if (!tipo.equals("All")) 
		  	funcion+=2;
		
		/*if(!tipo.equals(""))
			funcion-=2;*/
		
		if(precio!=0)
		 	funcion+=4; 
		  
		  switch (funcion){
		  	/*default: lista=mascotaRepositorio.findAll(pagerequest);
		  			break;*/
		  	case 1: lista = productoRepositorio.findByName(nombre, pagerequest);
		  			break;
		  	case 2: lista = productoRepositorio.findByCategory(tipo, pagerequest);
		  			break;
		  	case 3: lista = productoRepositorio.findByNameAndCategory(nombre, tipo, pagerequest);
	  				break;
		  	case 4: lista=productoRepositorio.findByPriceBetween(0, precioaux, pagerequest);
  					break;
		  	case 5: lista=productoRepositorio.findByNameAndPriceBetween(nombre, 0, precioaux, pagerequest);
  					break;
		  	case 6: lista=productoRepositorio.findByCategoryAndPriceBetween(tipo, 0, precioaux, pagerequest);
  					break;
		  	case 7:	lista=productoRepositorio.findByNameAndCategoryAndPriceBetween(nombre, tipo, 0, precioaux, pagerequest);
  					break;
		  	default: lista=productoRepositorio.findAll(pagerequest);
  			
		  	}
		  
		/*if (precio==0) {
			
			if (tipo.equals("All")) {
				if(nombre.equals("")) {
					lista=productoRepositorio.findAll(new PageRequest(numPag, numElem));
				
				}else {
					lista=productoRepositorio.findByName(nombre,new PageRequest(numPag, numElem));
					
				}
			}else {
				if(nombre.equals("")) {
					lista=productoRepositorio.findByCategory(tipo,new PageRequest(numPag, numElem));
				
				}else {
					lista=productoRepositorio.findByNameAndCategory(nombre,tipo,new PageRequest(numPag, numElem));
				
				}
			}
		}else {
			System.out.println("precio no 0");
			int precioaux=precio+1;
			if (tipo.equals("All")) {
				if(nombre.equals("")) {
					System.out.println("buscando");
					lista=productoRepositorio.findByPriceBetween(0,precioaux,new PageRequest(numPag, numElem));
				}else {
					lista=productoRepositorio.findByNameAndPriceBetween(nombre,0,precioaux,new PageRequest(numPag, numElem));
				}
			}else {
				if(nombre.equals("")) {
					lista=productoRepositorio.findByCategoryAndPriceBetween(tipo,0,precioaux,new PageRequest(numPag, numElem));
				}else {
					lista=productoRepositorio.findByNameAndCategoryAndPriceBetween(nombre,tipo,0,precioaux,new PageRequest(numPag, numElem));
				}
			}
		}*/
		
		model.addAttribute("nombre",nombre);
		model.addAttribute("tipo", tipo);
		model.addAttribute("precio",precio);
		model.addAttribute("productos",lista);
		model.addAttribute("numPag", sigPag);
		//model.addAttribute("carrito",carrito);
		return "catalogobusqueda_template";
	}
	
	@GetMapping ("/busqueda_producto")
	public String BusquedaProducto (Model model) {
		return "busquedaproducto_template";
	}
	
	@GetMapping ("/alta_producto")
	public String  AltaProducto(Model model) {
		return "altaproducto_template";
	}
	
	@GetMapping ("/registrar_producto")
	public String  RegistrarProducto(Model model, @RequestParam String nombre,@RequestParam int precio,@RequestParam String tipo,@RequestParam String descripcion,@RequestParam int stock) {
		Page<Producto> producto=productoRepositorio.findByName(nombre,new PageRequest(1, numElem));
		if (producto.isEmpty()){
		Producto producto_nuevo = new Producto(nombre, precio,descripcion,tipo,stock); 
		productoRepositorio.save(producto_nuevo);
		return "registroexitoso_template";
		}else {
			model.addAttribute("error", true);
			return "altaproducto_template";
		}
	}
		

	@GetMapping("/borrar_producto")
	public String BorrarProducto(Model model, @RequestParam String id) {
		System.out.println(id);
		long longID=Long.parseLong(id);
		Optional<Producto> optional=productoRepositorio.findById(longID);
		Producto producto= optional.get();
		productoRepositorio.delete(producto);
		return "borradoexitoso_template";
		
	}
	
	@GetMapping("/modificar_producto")
	public String ModificarProducto(Model model, @RequestParam String id) {
		System.out.println(id);
		long longID=Long.parseLong(id);
		Optional<Producto> optional=productoRepositorio.findById(longID);
		Producto producto= optional.get();
		model.addAttribute("id", id);
		model.addAttribute("nombre", producto.getName());
		model.addAttribute("precio", producto.getPrice());
		model.addAttribute("tipo", producto.getCategory());
		model.addAttribute("descripcion", producto.getDescription());
		model.addAttribute("stock", producto.getStock());
			return "modificarproducto_template";
	}
	@GetMapping("/cambiar_producto")
	public String cambiarProducto(Model model, @RequestParam String id,@RequestParam String name,@RequestParam int price,@RequestParam String category,@RequestParam String description,@RequestParam int stock) {
		long longID=Long.parseLong(id);
		Optional<Producto> optional = productoRepositorio.findById(longID); 
		Producto producto = optional.get();

		producto.setName(name);
		producto.setPrice(price);
		producto.setCategory(category);
		producto.setDescription(description);
		producto.setStock(stock);
		
		productoRepositorio.save(producto);
		return "cambioexitoso_template";
	}
			
		
}

