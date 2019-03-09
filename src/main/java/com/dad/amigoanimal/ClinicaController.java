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
public class ClinicaController {

	//@Autowired
	//private MascotaRepository mascotaRepositorio;
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	@Autowired
	private ClinicaRepository clinicaRepositorio;
	@Autowired
	private MascotaRepository mascotaRepositorio;
	@PostConstruct
	public void init() {
		clinicaRepositorio.save(new Clinica("Las Aguilas",3));
		
		
		
	}

	
	@RequestMapping ("/clinica")
	public String clinicController (Model model) {

		return "clinica_template";
	}
	
	@GetMapping ("/registrar_mascota")
	public String registrarMascota (Model model) {
		//model.addAttribute("alta",true);
		return "clinicaAlta_template";
	}
	
	@GetMapping ("/retirar_mascota")
	public String  retirarMascota(Model model) {
		//model.addAttribute("alta",false);
		return "clinicaBaja_template";
	}

	@GetMapping ("/mascota_alta")
	public String altaMascota(Model model, @RequestParam String ownerDoc, @RequestParam String name, @RequestParam String especie, @RequestParam String raza, @RequestParam String color) {
		//model.addAttribute("alta",false);
		List<Usuario> user;
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
			
			System.out.println("Mascota " + name + " de " + ownerDoc + " registrada con exito");
			return "greeting_template";
		}
		System.out.println("No se encuentra el dueño");
		return "clinicaAlta_template";
	}
	
	@GetMapping ("/mascota_baja")
	public String bajaMascota(Model model, @RequestParam String ownerDoc, @RequestParam String name/*, @RequestParam String especie, @RequestParam String raza, @RequestParam String color*/) {
		//model.addAttribute("alta",false);
		List<Mascota> mascota;
		mascota = mascotaRepositorio.findByNameAndUsuario(name, usuarioRepositorio.findByDocument(ownerDoc).get(0));
		if (mascota.size()>0) {
			mascotaRepositorio.delete(mascota.get(0));;
			//clinicaRepositorio.deleteMascota(mascotaNueva);
			System.out.println("Mascota " + name + " de " + ownerDoc + " retirada con exito");
			return "greeting_template";
		}
		System.out.println("No se encuentra el dueño");
		return "clinicaBaja_template";
	}
	
}
