package com.dad.amigoanimal;

import java.util.ArrayList;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	private HashMap<Long, Integer> carrete;
	
	
	@RequestMapping("/crear_carrito")
	public String crearCarrito (Model model) {
		//Carrito carrito= new Carrito();
		Page<Producto> lista = productoRepositorio.findAll(new PageRequest(0, numElem));
		model.addAttribute("productos",lista);
		//model.addAttribute("carrito",carrito);
		model.addAttribute("numPag", 1);
		
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
		usuario = userRepository.findByName(nombre).get();
		int precio = precioTotal(usuario);
		if (precio==0) {
			model.addAttribute("vacio", true);
		}
		model.addAttribute("lista", listar(usuario));
		model.addAttribute("precio", precio);
		
		return "carrito_template";
		
	}
	private List<ObjetoCarrito> listar(Cliente usuario) {
		List <ObjetoCarrito>lista = new ArrayList();
		Set<Entry<Long, Integer>> contenido = usuario.getMap().entrySet();
		for(Entry<Long,Integer> entrada:contenido) {
			lista.add(new ObjetoCarrito(entrada.getKey(),productoRepositorio.findById(entrada.getKey()).get().getName(),entrada.getValue()));
		}
		return lista;
	}
	private int precioTotal(Cliente usuario) {
		int resultado= 0;
		Set<Entry<Long, Integer>> contenido = usuario.getMap().entrySet();
		for(Entry<Long,Integer> entrada:contenido) {
			resultado+= productoRepositorio.findById(entrada.getKey()).get().getPrice()*entrada.getValue();
		}
		return resultado;
	}
	@RequestMapping ("/aniadir_carrito")
	public String aniadirCarrito (Model model, String id, int quantity, /*Carrito carrito,*/ HttpServletRequest request) {

		long longID=Long.parseLong(id);
		nombre = request.getUserPrincipal().getName();
		usuario = userRepository.findByName(nombre).get();
		if(productoRepositorio.findById(longID).get().getStock()<quantity) {
			Page<Producto> lista = productoRepositorio.findAll(new PageRequest(0, numElem));
			model.addAttribute("productos",lista);
			model.addAttribute("fallo", true);
			model.addAttribute("numPag", 0);

			return "catalogo_template";
		}else {
		usuario.addProducto2(longID, quantity);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getPrincipal() + " añade producto " + productoRepositorio.findById(longID).get().getName() + " al carrito");
		
		Page<Producto> lista = productoRepositorio.findAll(new PageRequest(0, numElem));
		model.addAttribute("productos",lista);

		model.addAttribute("numPag", 0);
		return "catalogo_template";}
	}
	
	@RequestMapping ("/eliminar_carrito")
	public String eliminarCarrito (Model model, String id, int quantity, HttpServletRequest request) {
		long longID=Long.parseLong(id);
		nombre = request.getUserPrincipal().getName();
		usuario = userRepository.findByName(nombre).get();
		Producto producto=productoRepositorio.findById(longID).get();
		// Establecer que carrito funciona y trabajar respecto a ello
		usuario.removeProducto(longID, quantity);
		int precio = precioTotal(usuario);
		if (precio==0) {
			model.addAttribute("vacio", true);
		}
		model.addAttribute("lista",listar(usuario));
		model.addAttribute("precio", precio);
		
		return "carrito_template";
	}

	@RequestMapping ("/enviar_carrito")
	public String enviarCarrito (Model model, /*Carrito carrito,*/ HttpServletRequest request) {
		HashMap<Producto,Integer>envio = new HashMap<Producto,Integer>();
		nombre = request.getUserPrincipal().getName();
		usuario = userRepository.findByName(nombre).get();
		Set<Entry<Long, Integer>> contenido = usuario.getMap().entrySet();
		for(Entry<Long,Integer> entrada:contenido) {
			Producto producto=productoRepositorio.findById(entrada.getKey()).get();
			producto.setStock(producto.getStock()-entrada.getValue());
			productoRepositorio.save(producto);
			envio.put(producto,entrada.getValue());
		}
		// Establecer que carrito funciona y trabajar respecto a ello
		ClienteSocket.enviarSocket(usuario, envio);
		return "confirmacioncompra_template";
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
		  
		
		
		model.addAttribute("nombre",nombre);
		model.addAttribute("tipo", tipo);
		model.addAttribute("precio",precio);
		model.addAttribute("productos",lista);
		model.addAttribute("numPag", sigPag);
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
		model.addAttribute("catalogo", true);
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
		model.addAttribute("catalogo", true);
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
		model.addAttribute("catalogo", true);
		return "cambioexitoso_template";
	}
			
	
	
}

