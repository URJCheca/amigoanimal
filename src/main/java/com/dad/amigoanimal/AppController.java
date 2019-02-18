package com.dad.amigoanimal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//La clase contendra todas las redirecciones posibles desde la pagina principal
@Controller
public class AppController {
	
	
	@Autowired
	private ProductoRepository productoRepositorio;
	
	//Redirecciona a la pagina principal
	@RequestMapping ("/greeting")
	public String greetingController (Model model) {
		return "greeting_template";
	}
	//Redirecciona a la pagina de login
	@RequestMapping ("/signin")
	public String loginController (Model model) {
		return "signin_template";
	}
	
	@RequestMapping ("/verify_signin")
	public String varifySigninController (Model model,@RequestParam String usuario, @RequestParam String contrasena) {
			
		model.addAttribute("nombre_provisional",usuario);
		model.addAttribute ("password_provisional", contrasena);
		model.addAttribute("fail", true);
		
		return "sin_template";
	}
	//Redirecciona a la pagina de registro
	@RequestMapping ("/signup")
	public String registerController (Model model) {
		return "signup_template";
	}
	
	@RequestMapping ("/verify_signup")
	public String verifySignupController (Model model,@RequestParam String usuario, @RequestParam String contrasena,
			@RequestParam String contrasena2,@RequestParam String email) {
		if (contrasena.equals(contrasena2)){
			
			model.addAttribute("nombre_provisional",usuario);
			model.addAttribute ("email_provisional", email);
			model.addAttribute ("password_provisional", contrasena);
			return "sin_template";
		}
		model.addAttribute("password",true);
		return "signup_template";
	}
	
	@RequestMapping ("/catalogo")
	public String catalogoController (Model model) {
		List<Producto> lista = productoRepositorio.findAll();
		model.addAttribute("productos",lista);
		
		return "catalogo_template";
	}
	@RequestMapping ("/clinica")
	public String clinicaController (Model model) {
		return "clinica_template";
	}
	

}
