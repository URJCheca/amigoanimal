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
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
	@PostConstruct
	public void init() {
		usuarioRepositorio.save(new Usuario("alex","alex123","46789143l","alex@hotmail.com",1));
		usuarioRepositorio.save(new Usuario("manu","manu123","55098788p","manu@gmail.com",1));
		usuarioRepositorio.save(new Usuario("pepe","pepe123","22750912s","pepe@gmail.com",1));
			
	}
	
	@RequestMapping("/signup")
	public String registerController (Model model) {
		//List<Usuario> lista = usuarioRepositorio.findAll();
		//model.addAttribute("usuarios",lista);
		return "signup_template";
	}
	
	@RequestMapping("/signin")
	public String loginController (Model model) {
		//List<Usuario> lista = usuarioRepositorio.findAll();
		//model.addAttribute("usuarios",lista);
		return "signin_template";
	}
	
	@GetMapping("/registrar_user")
	public String  RegistrarUsuario(Model model, @RequestParam String nombre, @RequestParam String contrasena, @RequestParam String contrasena2, @RequestParam String email,@RequestParam String documento) {
		if (contrasena.equals(contrasena2)) {
			Usuario usuario_nuevo = new Usuario(nombre, contrasena, documento, email, 1); 
			usuarioRepositorio.save(usuario_nuevo);
			System.out.println("Registrado con exito: " + nombre);
			return "greeting_template";
		} else {
			System.out.println("Contraseñas diferentes");
			return "signup_template";
		}
	}
	
	@GetMapping("/user_login")
	public String  LogearUsuario(Model model, @RequestParam String nombre, @RequestParam String contrasena) {
		
		List<Usuario> user;
		user = usuarioRepositorio.findByName(nombre);
		
		if (user.size()>0) {
			if (user.get(0).getContrasena().equals(contrasena)) {
				System.out.println("Logeado con exito: " + nombre);
				return "greeting_template";
			} /*else {
				System.out.println("Contraseña equivocada");
			}*/
		}
		System.out.println("Usuario o contraseña no compatibles ");
		return "signin_template";
	}
	
}
