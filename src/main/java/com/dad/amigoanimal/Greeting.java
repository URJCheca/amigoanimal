package com.dad.amigoanimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//La clase contendra todas las redirecciones posibles desde la pagina principal
@Controller
public class Greeting {
	//Redirecciona a la pagina principal
	@RequestMapping ("/greeting")
	public String greetingController (Model model) {
		return "greeting_template";
	}
	//Redirecciona a la pagina de login
	@RequestMapping ("/login")
	public String loginController (Model model) {
		return "login_template";
	}
	//Redirecciona a la pagina de registro
	@RequestMapping ("/register")
	public String registerController (Model model) {
		return "register_template";
	}
	

}
