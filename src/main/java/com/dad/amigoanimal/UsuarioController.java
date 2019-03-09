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
	private ClienteRepository clienteRepositorio;
	@Autowired
	private ClinicaRepository clinicaRepositorio;
	@Autowired
	private MascotaRepository mascotaRepositorio;
	@Autowired
	private UserBaseRepository usuarioRepositorio;
	
	@PostConstruct
	public void init() {
		Cliente cliente1 =new Cliente("alex","alex","alex123","46789143l","alex@hotmail.com",1);
		Cliente cliente2 =new Cliente("manu","manu","manu123","55098788p","manu@gmail.com",1);
		Cliente cliente3 =new Cliente("pepe","pepe","pepe123","22750912s","pepe@gmail.com",1);
		
		
		Mascota mascota1=new Mascota("Congo","Loro","Yaco","Gris");
		Mascota mascota2=new Mascota("Fluffy","perro","labrador","canela");
		Mascota mascota3=new Mascota("Miaustache","gato","persa","gris");
		Mascota mascota4=new Mascota("Calcetines","gato","europeo","negro");
		
		
		clienteRepositorio.save(cliente1);
		clienteRepositorio.save(cliente2);
		clienteRepositorio.save(cliente3);
		
		mascota1.setUsuario(cliente1);
		mascota2.setUsuario(cliente2);
		mascota3.setUsuario(cliente2);
		mascota4.setUsuario(cliente3);
		/*cliente1.addMascota(mascota1);;
		cliente2.addMascota(mascota2);
		cliente3.addMascota(mascota3);
		cliente3.addMascota(mascota4);*/
				
		
		mascotaRepositorio.save(mascota1);
		mascotaRepositorio.save(mascota2);
		mascotaRepositorio.save(mascota3);
		mascotaRepositorio.save(mascota4);
		/*Usuario user = new Usuario("123","123","123123","123123123","123@gmail.com",3);		
		
		user.setClinica(clinicaRepositorio.findByName("Las Aguilas").get(0));
		usuarioRepositorio.save(user);*/
			
	}
	
	@RequestMapping("/signin")
	public String loginController (Model model) {
		return "signin_template";
	}
	
	@GetMapping("/user_login")
	public String  LogearUsuario(Model model, @RequestParam String nombre, @RequestParam String contrasena) {
		
		List<Cliente> user;
		user = clienteRepositorio.findByName(nombre);
		
		if (user.size()>0) {
			if (user.get(0).getContrasena().equals(contrasena)) {
				System.out.println("Logeado con exito: " + nombre);
				return "greeting_template";
			} /*else {
				System.out.println("Contraseña equivocada");
			}*/
		}
		model.addAttribute("fail", true);
		return "signin_template";
	}
	
	@RequestMapping("/signup")
	public String registerController (Model model) {
		return "signup_template";
	}
	
	@GetMapping("/registrar_user")
	public String  RegistrarUsuario(Model model, @RequestParam String login, @RequestParam String contrasena, @RequestParam String contrasena2, @RequestParam String email) {
		Boolean coincideLogin=clienteRepositorio.findByLogin(login).isPresent();
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
		
			Cliente usuario_nuevo = new Cliente(login,nombre, contrasena, documento, email, 1); 
			clienteRepositorio.save(usuario_nuevo);
			System.out.println("Registrado con exito: " + nombre);
			return "greeting_template";
		
		
	}
	
	
	
	
}
