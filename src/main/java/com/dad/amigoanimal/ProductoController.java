package com.dad.amigoanimal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepositorio;

	@GetMapping ("/busqueda_avanzada_producto")
	public String BusquedaAvanzada (Model model,@RequestParam String nombre,@RequestParam String tipo,@RequestParam float precio) {
		//realizar para cada una de las posibles busquedas
		List<Producto> lista = productoRepositorio.findAll();
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
	public String  RegistrarProducto(Model model, @RequestParam String nombre,@RequestParam float precio,@RequestParam String tipo,@RequestParam String descripcion,
			@RequestParam int stock) {
		Producto producto_nuevo = new Producto(nombre, precio,descripcion,tipo,stock); 
		productoRepositorio.save(producto_nuevo);
		return "registroexitoso_template";
		
		
		
		
		
		
	}
	
	
}
