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
	@Autowired
	private ClinicaRepository clinicaRepositorio;
	
	@PostConstruct
	public void init() {
		usuarioRepositorio.save(new Usuario("alex","alex","alex123","46789143l","alex@hotmail.com",1));
		usuarioRepositorio.save(new Usuario("manu","manu","manu123","55098788p","manu@gmail.com",1));
		usuarioRepositorio.save(new Usuario("pepe","pepe","pepe123","22750912s","pepe@gmail.com",1));
		Usuario user = new Usuario("123","123","123123","123123123","123@gmail.com",3);		
		user.setClinica(clinicaRepositorio.findByName("Las Aguilas").get(0));
		usuarioRepositorio.save(user);
			
	}
	
	@RequestMapping("/signin")
	public String loginController (Model model) {
		return "signin_template";
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
	
	@RequestMapping("/signup")
	public String registerController (Model model) {
		return "signup_template";
	}
	
	@GetMapping("/registrar_user")
	public String  RegistrarUsuario(Model model, @RequestParam String login, @RequestParam String contrasena, @RequestParam String contrasena2, @RequestParam String email) {
		Boolean coincideLogin=usuarioRepositorio.findByLogin(login).isPresent();
		System.out.println("llego aqui");
		if(!coincideLogin) {
			System.out.println("llego aqui???");
			if (contrasena.equals(contrasena2)) {
				model.addAttribute("login", login);
				model.addAttribute("contrasena", contrasena);
				model.addAttribute("email", email);
				return "verifysingup_template";
			}
				model.addAttribute("error", true);
				return "signup_template";	
		}
		model.addAttribute("usado", true);
		return "signup_template";	
	}
	
	@GetMapping("/continuacion_registro")
	public String  ContinuacionRegistro(Model model, @RequestParam String login,@RequestParam String nombre, @RequestParam String contrasena, @RequestParam String email,@RequestParam String documento) {
		
			Usuario usuario_nuevo = new Usuario(login,nombre, contrasena, documento, email, 1); 
			usuarioRepositorio.save(usuario_nuevo);
			System.out.println("Registrado con exito: " + nombre);
			return "greeting_template";
		
		
	}
	
	
	
	
}
