package com.dad.amigoanimal;

import java.util.List;

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
	public String altaMascota(Model model, @RequestParam String owner, @RequestParam String name, @RequestParam String especie, @RequestParam String raza, @RequestParam String color) {
		//model.addAttribute("alta",false);
		List<Usuario> user;
		user = usuarioRepositorio.findByName(owner);
		if (user.size()>0) {
			Mascota mascotaNueva = new Mascota(user.get(0), name, especie, raza, color);
			//clinicaRepositorio.saveMascota(mascotaNueva);
			System.out.println("Mascota " + name + " de " + owner + " registrada con exito");
			return "greeting_template";
		}
		System.out.println("No se encuentra el dueño");
		return "clinicaAlta_template";
	}
	
	@GetMapping ("/mascota_baja")
	public String bajaMascota(Model model, @RequestParam String owner, @RequestParam String name, @RequestParam String especie, @RequestParam String raza, @RequestParam String color) {
		//model.addAttribute("alta",false);
		List<Usuario> user;
		user = usuarioRepositorio.findByName(owner);
		if (user.size()>0) {
			Mascota mascotaNueva = new Mascota(user.get(0), name, especie, raza, color);
			//clinicaRepositorio.deleteMascota(mascotaNueva);
			System.out.println("Mascota " + name + " de " + owner + " retirada con exito");
			return "greeting_template";
		}
		System.out.println("No se encuentra el dueño");
		return "clinicaBaja_template";
	}
	
}
