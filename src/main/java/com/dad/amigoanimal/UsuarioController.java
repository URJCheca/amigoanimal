package com.dad.amigoanimal;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
	private TrabajadorRepository trabajadorRepositorio;
	
	@PostConstruct
	public void init() {
		
		
			
	}
	
	@GetMapping("/signin")
	public String loginController (Model model) {
		return "signin_template";
	}
	
	@GetMapping("/signed")
	public String logedController (Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getPrincipal() + " se logea");
		return "greeting_template";
	}
	
	@GetMapping ("/signin_fail")
	public String loginFail (Model model) {
		model.addAttribute("fail", true);
		System.out.println("fallo");
		return "signin_template";
	}
	
	@GetMapping ("/logout")
	public String cerrarSesion (Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	    	System.out.println(auth.getPrincipal() + " se deslogea");
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		return "greeting_template";
	}
		
	
	@RequestMapping("/signup")
	public String registerController (Model model) {
		return "signup_template";
	}
	
	
	
	@GetMapping("/continuacion_registro")
	public String  ContinuacionRegistro(Model model, @RequestParam String login,@RequestParam String nombre, @RequestParam String contrasena, @RequestParam String email,@RequestParam String documento) {
		
			Cliente usuario_nuevo = new Cliente(login,nombre, contrasena, documento, email, "ROLE_USER"); 
			clienteRepositorio.save(usuario_nuevo);
			System.out.println("Registrado con exito: " + nombre);
			model.addAttribute("usuario", true);
			return "registroexitoso_template";
		
		
	}
	@GetMapping("/administrar")
	public String  Administrar(Model model) {
		return "busquedausuario_template";
	}
	
	@GetMapping("/busqueda_usuario")
	public String  BusquedaUsuario(Model model, @RequestParam String login) {
			 Optional<Cliente> optionalC=  clienteRepositorio.findByLogin(login);
			if (optionalC.isPresent()) {
				Cliente cliente = (Cliente) optionalC.get();
				model.addAttribute("login", cliente.getLogin());
				model.addAttribute("document", cliente.getDocument());
				model.addAttribute("nombre", cliente.getName());
				model.addAttribute("email", cliente.getEmail());
				model.addAttribute("lista", cliente.getClinica());
				model.addAttribute("rol", "Cliente");
				model.addAttribute("cliente", true);
			return "resultadousuario_template";
			}
			Optional<Trabajador>optionalT=  trabajadorRepositorio.findByLogin(login);
				if (optionalT.isPresent()) {
					Trabajador trabajador = (Trabajador) optionalT.get();
					model.addAttribute("login", trabajador.getLogin());
					model.addAttribute("document", trabajador.getDocument());
					model.addAttribute("nombre", trabajador.getName());
					model.addAttribute("email", trabajador.getEmail());
					model.addAttribute("lista", trabajador.getClinica());
					model.addAttribute("rol", "Trabajador");
					model.addAttribute("trabajador", true);
					return "resultadousuario_template";
			}
			model.addAttribute("fail",true)	;
			return "busquedausuario_template";
		
		
	}
	
	@GetMapping("/cambiar_rol")
	public String  CambiarRol(Model model, @RequestParam String login) {
			 Optional<Cliente> optionalC=  clienteRepositorio.findByLogin(login);
			if (optionalC.isPresent()) {
				Cliente cliente = (Cliente) optionalC.get();
				Trabajador trabajador = new Trabajador(cliente.getLogin(), cliente.getName(), cliente.getcontrasena(), cliente.getDocument(),cliente.getEmail(), "ROLE_ADMIN");
				trabajadorRepositorio.save(trabajador);
				clienteRepositorio.delete(cliente);
			}else {
				Optional<Trabajador>optionalT=  trabajadorRepositorio.findByLogin(login);
				if (optionalT.isPresent()) {
					Trabajador trabajador = (Trabajador) optionalT.get();
					Cliente cliente = new Cliente(trabajador.getLogin(), trabajador.getName(), trabajador.getcontrasena(), trabajador.getDocument(),trabajador.getEmail(), "ROLE_USER");
					clienteRepositorio.save(cliente);
					trabajadorRepositorio.delete(trabajador);
				}
			}
			model.addAttribute("usuario",true);
			return "cambioexitoso_template";
		
		
	}
	
	@GetMapping("/borrar_usuario")
	public String  BorrarUsuario(Model model, @RequestParam String login) {
			 Optional<Cliente> optionalC=  clienteRepositorio.findByLogin(login);
			if (optionalC.isPresent()) {
				Cliente cliente = (Cliente) optionalC.get();
				clienteRepositorio.delete(cliente);
			}else {
				Optional<Trabajador>optionalT=  trabajadorRepositorio.findByLogin(login);
				if (optionalT.isPresent()) {
					Trabajador trabajador = (Trabajador) optionalT.get();
					trabajadorRepositorio.delete(trabajador);
				}
			}
			model.addAttribute("usuario", true);
			return "borradoexitoso_template";	
	}
	@GetMapping("/borrar_clinica")
	public String  BorrarClinica(Model model, @RequestParam String nombre,@RequestParam String login) {
			Clinica clinica = null;
			System.out.print(nombre);
			Optional<Clinica>optionalClinic = clinicaRepositorio.findByNombre(nombre);
			if (optionalClinic.isPresent()) {
				clinica=optionalClinic.get();
			}else {

				return "busquedausuario_template";
			}
			Optional<Cliente> optionalC=  clienteRepositorio.findByLogin(login);
			if (optionalC.isPresent()) {
				Cliente cliente = (Cliente) optionalC.get();
				cliente.getClinica().remove(clinica);
				clienteRepositorio.save(cliente);
				
			}else {
				Optional<Trabajador>optionalT=  trabajadorRepositorio.findByLogin(login);
				if (optionalT.isPresent()) {
					Trabajador trabajador = (Trabajador) optionalT.get();
					trabajador.setClinica(null);
					trabajadorRepositorio.save(trabajador);
				}
			}
			model.addAttribute("usuario", true);
			return "borradoexitoso_template";	
	}
	@GetMapping("/aniadir_clinica")
	public String  AddClinica(Model model, @RequestParam String nombre, @RequestParam String login) {
		Clinica clinica = null;
		System.out.print(nombre);
		Optional<Clinica>optionalClinic = clinicaRepositorio.findByNombre(nombre);
		if (optionalClinic.isPresent()) {
			clinica=optionalClinic.get();
		}else {

			return "busquedausuario_template";
		}
			Optional<Cliente> optionalC=  clienteRepositorio.findByLogin(login);
			if (optionalC.isPresent()) {
				Cliente cliente = (Cliente) optionalC.get();
				if(! cliente.getClinica().contains(clinica)) {
					cliente.setClinica(clinica);
					clienteRepositorio.save(cliente);
				}
			}else {
				Optional<Trabajador>optionalT=  trabajadorRepositorio.findByLogin(login);
				if (optionalT.isPresent()) {
					Trabajador trabajador = (Trabajador) optionalT.get();
					trabajador.setClinica(clinica);
					trabajadorRepositorio.save(trabajador);
				}
			}
			model.addAttribute("usuario", true);
			return "registroexitoso_template";	
	}
	
	
	
	
	
	
	
}
