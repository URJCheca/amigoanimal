package com.dad.amigoanimal;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepositorio;
	
	@PostConstruct
	public void init() {
		productoRepositorio.save(new Producto("Catzilla", 14, "Comida para gatos", "Alimentacion", 5));
		System.out.print("se ha creado el producto");
	}
	@RequestMapping ("/catalogo")
	public String catalogoController (Model model) {
		List<Producto> lista = productoRepositorio.findAll();
		model.addAttribute("productos",lista);
		
		return "catalogo_template";
	}
	
	@GetMapping ("/busqueda_avanzada_producto")
	public String BusquedaAvanzada (Model model,@RequestParam String nombre,@RequestParam String tipo,@RequestParam int precio) {
		System.out.println (nombre+" "+tipo+" "+precio);
		List<Producto> lista;
		
		//realizar para cada una de las posibles busquedas
		
		if (precio==0) {
			precio=precio+1;
			if (tipo.equals("All")) {
				if(nombre=="") {
					lista=productoRepositorio.findAll();
				
				}else {
					lista=productoRepositorio.findByName(nombre);
					
				}
			}else {
				if(nombre=="") {
					lista=productoRepositorio.findByCategory(tipo);
				
				}else {
					lista=productoRepositorio.findByNameAndCategory(nombre,tipo);
				
				}
			}
		}else {
			
			if (tipo=="All") {
				if(nombre=="") {
					lista=productoRepositorio.findByPriceBetween(0,precio);
				}else {
					lista=productoRepositorio.findByNameAndPriceBetween(nombre,0,precio);
				}
			}else {
				if(nombre=="") {
					lista=productoRepositorio.findByCategoryAndPriceBetween(tipo,0,precio);
				}else {
					lista=productoRepositorio.findByNameAndCategoryAndPriceBetween(nombre,tipo,0,precio);
				}
			}
		}
		
		model.addAttribute("productos",lista);
		return "catalogo_template";
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
		List<Producto> producto=productoRepositorio.findByName(nombre);
		if (producto.isEmpty()){
		Producto producto_nuevo = new Producto(nombre, precio,descripcion,tipo,stock); 
		productoRepositorio.save(producto_nuevo);
		return "registroexitoso_template";
		}else {
			model.addAttribute("error", true);
			return "altaproducto_template";
		}
	}
		
	@GetMapping("/baja_producto")
	public String BajaProducto(Model model) {
			return "bajaproducto_template";
	}
	@GetMapping("/borrar_producto")
	public String BorrarProducto(Model model, @RequestParam String nombre) {
		List<Producto> producto=productoRepositorio.findByName(nombre);
		if (producto.isEmpty()){
			model.addAttribute("error", true);
		return "bajaproducto_template";
		}else {
			productoRepositorio.delete(producto.get(0));
			return "barradoexitoso_template";
		}
	}
			
		
		
		
		
	}

