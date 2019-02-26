package com.dad.amigoanimal;

import java.util.List;

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
			return "greeting_template";
		} else {
			return "signup_template";
		}
	}
	
	@GetMapping("/user_login")
	public String  LogearUsuario(Model model, @RequestParam String nombre, @RequestParam String contrasena) {
		
		List<Usuario> user;
		user = usuarioRepositorio.findByName(nombre);
		
		if (user.size()>0) {
			if (user.get(0).getContrasena().equals(contrasena)) {
				return "greeting_template";
			}
		}
		
		return "signin_template";
	}
	
}
