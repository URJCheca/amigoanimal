package com.dad.amigoanimal;

import java.util.List;

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
	
	@RequestMapping ("/catalogo")
	public String catalogoController (Model model) {
		List<Producto> lista = productoRepositorio.findAll();
		model.addAttribute("productos",lista);
		
		return "catalogo_template";
	}
	
	@GetMapping ("/busqueda_avanzada_producto")
	public String BusquedaAvanzada (Model model,@RequestParam String nombre,@RequestParam String tipo,@RequestParam int precio) {
		List<Producto> lista;
		precio=precio+1;
		//realizar para cada una de las posibles busquedas
		System.out.println("el nombre introducido es: "+nombre);
		if (precio==0) {
			if (tipo=="All") {
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
		Producto producto_nuevo = new Producto(nombre, precio,descripcion,tipo,stock); 
		productoRepositorio.save(producto_nuevo);
		return "registroexitoso_template";
		
		
		
		
		
		
	}
	
	
}
