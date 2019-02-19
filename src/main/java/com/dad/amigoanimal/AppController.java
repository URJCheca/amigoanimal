package com.dad.amigoanimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

//La clase contendra todas las redirecciones posibles desde la pagina principal
@Controller
public class AppController {
	//Redirecciona a la pagina principal
	@PostMapping ("/greeting")
	public String greetingController (Model model) {
		return "greeting_template";
	}
	//Redirecciona a la pagina de login
	@PostMapping ("/signin")
	public String loginController (Model model) {
		return "signin_template";
	}
	
	@PostMapping ("/verify_signin")
	public String varifySigninController (Model model,@RequestBody String usuario, @RequestBody String contrasena) {
			
		model.addAttribute("nombre_provisional",usuario);
		model.addAttribute ("password_provisional", contrasena);
		model.addAttribute("fail", true);
		
		return "sin_template";
	}
	//Redirecciona a la pagina de registro
	@PostMapping ("/signup")
	public String registerController (Model model) {
		return "signup_template";
	}
	
	@PostMapping ("/verify_signup")
	public String varifySignupController (Model model,@RequestBody String usuario, @RequestBody String contrasena,
			@RequestBody String contrasena2,@RequestBody String email) {
		if (contrasena.equals(contrasena2)){
			
			model.addAttribute("nombre_provisional",usuario);
			model.addAttribute ("email_provisional", email);
			model.addAttribute ("password_provisional", contrasena);
			return "sin_template";
		}
		model.addAttribute("password",true);
		return "signup_template";
	}
	
	@PostMapping ("/catalogo")
	public String catalogoController (Model model) {
		return "catalogo_template";
	}
	@PostMapping ("/clinica")
	public String clinicaController (Model model) {
		return "clinica_template";
	}
	

}
