package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClinicaController {

	//@Autowired
	//private MascotaRepository mascotaRepositorio;
	@Autowired
	private ClienteRepository usuarioRepositorio;
	@Autowired
	private ClinicaRepository clinicaRepositorio;
	@Autowired
	private MascotaRepository mascotaRepositorio;
	
	int numElem= 2;
	
	@PostConstruct
	public void init() {
		clinicaRepositorio.save(new Clinica("Las Aguilas",3));
	/*	
		mascotaRepositorio.save(new Mascota("Flufy"));
		mascotaRepositorio.save(new Mascota("Transportin grande", 20, "Transportin de grandes dimensiones para perros", "Viaje", 5));
		mascotaRepositorio.save(new Mascota("Bun bunny", 5, "Complemento alimenticio para conejos", "Alimentacion",20));
		mascotaRepositorio.save(new Mascota("Arenero", 10, "Arenero para gatos", "Higiene", 15));
		mascotaRepositorio.save(new Mascota("Anti-lombrices", 8, "Pastillas anti parasitarias para perros y gatos", "Salud", 50));
		usuarioRepositorio.save(new Cliente("alex","alex","alex123","46789143l","alex@hotmail.com",1));
		usuarioRepositorio.save(new Cliente("manu","manu","manu123","55098788p","manu@gmail.com",1));
		usuarioRepositorio.save(new Cliente("pepe","pepe","pepe123","22750912s","pepe@gmail.com",1));
		
		*/
	}
	
	@RequestMapping ("/clinica")
	public String clinicaController (Model model) {

		return "clinica_template";
	}
	
	//Cuando tengamos persistencia de sesion, buscar las mascotas en la clinica del trabajador
	@RequestMapping ("/registro_mascotas")
	public String registroMascotas (Model model,@RequestParam int numPag) {
		Page<Mascota> lista=mascotaRepositorio.findAll(new PageRequest(numPag, numElem));
		model.addAttribute("mascotas", lista);
		model.addAttribute("numPag", numPag+1);
		return "mascotas_template";
	}
	@GetMapping ("/registrar_mascota")
	public String registrarMascota (Model model) {
		//model.addAttribute("alta",true);
		return "clinicaAlta_template";
	}
	
	
	/*@GetMapping ("/retirar_mascota")
	public String  retirarMascota(Model model) {
		//model.addAttribute("alta",false);
		return "clinicaBaja_template";
	}*/

	@GetMapping ("/mascota_alta")
	public String altaMascota(Model model, @RequestParam String ownerDoc, @RequestParam String name, @RequestParam String especie, @RequestParam String raza, @RequestParam String color) {
		//model.addAttribute("alta",false);
		List<Cliente> user;
		user = usuarioRepositorio.findByDocument(ownerDoc);
		if (user.size()>0) {
			Mascota mascotaNueva = new Mascota(name, especie, raza, color/*,clinicaRepositorio.findByName("Las Aguilas").get(0)*/);
			mascotaNueva.setUsuario(user.get(0));
			if (user.get(0).getClinica() != null) {
				System.out.println("Existe clinica del usuario");
				mascotaNueva.setClinica(user.get(0).getClinica());
			} else {
				mascotaNueva.setClinica(clinicaRepositorio.findByName("Las Aguilas").get(0)); 
				}
			mascotaRepositorio.save(mascotaNueva);
			model.addAttribute("mascota", true);
		
			return "registroexitoso_template";
		}
		System.out.println("No se encuentra el dueño");
		return "clinicaAlta_template";
	}
	
	@GetMapping ("/baja_mascota")
	public String bajaMascota(Model model, @RequestParam String id) {
		//model.addAttribute("alta",false);
		System.out.println(id);
		long longID=Long.parseLong(id);
		Optional<Mascota> optional=mascotaRepositorio.findById(longID);
		Mascota mascota= optional.get();
		mascotaRepositorio.delete(mascota);
		model.addAttribute("mascota", true);
		return "borradoexitoso_template";
	}
	
	@GetMapping("/modificar_mascota")
	public String ModificarProducto(Model model, @RequestParam String id) {
		System.out.println(id);
		long longID=Long.parseLong(id);
		Optional<Mascota> optional=mascotaRepositorio.findById(longID);
		Mascota mascota= optional.get();
		model.addAttribute("id", id);
		model.addAttribute("name", mascota.getName());
		model.addAttribute("especie", mascota.getEspecie());
		model.addAttribute("raza", mascota.getRaza());
		model.addAttribute("color", mascota.getColor());
		model.addAttribute("owner", mascota.getUsuario().getName());
			return "modificarmascota_template";
	}
	@GetMapping("/cambiar_mascota")
	public String cambiarProducto(Model model, @RequestParam String id,@RequestParam String name,@RequestParam String especie,@RequestParam String raza,@RequestParam String color) {
		long longID=Long.parseLong(id);
		Optional<Mascota> optional=mascotaRepositorio.findById(longID);
		Mascota mascota= optional.get();
		mascota.setName(name);
		mascota.setEspecie(especie);
		mascota.setRaza(raza);
		mascota.setColor(color); 
		mascotaRepositorio.save(mascota);
		model.addAttribute("mascota", true);
			return "cambioexitoso_template";
	}
	
}
