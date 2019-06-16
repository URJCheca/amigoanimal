package com.dad.amigoanimal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClinicaController {

	
	@Autowired
	private ClienteRepository usuarioRepositorio;
	@Autowired
	private ClinicaRepository clinicaRepositorio;
	@Autowired
	private MascotaRepository mascotaRepositorio;
	
	int numElem= 2;
	
	/*@PostConstruct
	public void init() {
		clinicaRepositorio.save(new Clinica("Las Aguilas",3));
	
	}*/
	
	@RequestMapping ("/clinica")
	public String clinicaController (Model model) {

		return "clinica_template";
	}
	
	//Cuando tengamos persistencia de sesion, buscar las mascotas en la clinica del trabajador
	@RequestMapping ("/registro_mascotas")
	public String registroMascotas (Model model,@RequestParam int numPag) {
		Page<Mascota> lista=mascotaRepositorio.findAll(new PageRequest(numPag, numElem));
		model.addAttribute("mascotas", lista);
		model.addAttribute("numPag", numPag+1);
		return "mascotas_template";
	}
	
	@GetMapping ("/busqueda_avanzada_mascota")
	public String BusquedaAvanzada (Model model,@RequestParam String nombre,@RequestParam String document,@RequestParam String clinica,@RequestParam String especie,@RequestParam int numPag) {
		Page<Mascota> lista;
		int sigPag= numPag+1;
		Cliente usuario;
		Clinica clinic;

		clinic=clinicaRepositorio.findByName(clinica);
		usuario= (Cliente) usuarioRepositorio.findByDocument(document);
		PageRequest pagerequest=new PageRequest(numPag, numElem);
		
	//Da un valor aa los parametros. Suma los valores si tienen contenido y accede al caso que le corresponda
		int funcion=0;
		
		if (!clinica.equals("")) { 
			funcion+=1;
			if (clinic==null) {
				model.addAttribute("clerror", true);
				return "busquedamascota_template";
			}
		}
		
		if (!document.equals("")) {
		  	funcion+=2;
		  	if (usuario==null) {
				model.addAttribute("userror", true);
				return "busquedamascota_template";
			}
		}
		
		if(!especie.equals(""))
		 	funcion+=4; 
		
		if(!nombre.equals("")) 
			funcion+=8;
		  
		  switch (funcion){
		  	case 1: lista = mascotaRepositorio.findByClinica(clinic, pagerequest);
		  			break;
		  	case 2: lista = mascotaRepositorio.findByUsuario(usuario, pagerequest);
		  			break;
		  	case 3: lista = mascotaRepositorio.findByUsuarioAndClinica(usuario, clinic, pagerequest);
	  				break;
		  	case 4: lista=mascotaRepositorio.findByEspecie(especie, pagerequest);
  					break;
		  	case 5: lista=mascotaRepositorio.findByEspecieAndClinica(especie, clinic, pagerequest);
  					break;
		  	case 6: lista=mascotaRepositorio.findByUsuarioAndEspecie(usuario, especie, pagerequest);
  					break;
		  	case 7:	lista=mascotaRepositorio.findByUsuarioAndEspecieAndClinica(usuario, especie, clinic, pagerequest);
  					break;
		  	case 8: lista=mascotaRepositorio.findByName(nombre,pagerequest);
					break;
		  	case 9: lista=mascotaRepositorio.findByNameAndClinica(nombre, clinic, pagerequest);
  					break;
		  	case 10:lista=mascotaRepositorio.findByNameAndUsuario(nombre, usuario, pagerequest);
  					break;
		  	case 11:lista=mascotaRepositorio.findByNameAndUsuarioAndClinica(nombre, usuario, clinic, pagerequest);
					break;
		  	case 12:lista=mascotaRepositorio.findByNameAndEspecie(nombre, especie, pagerequest);
					break;
		  	case 13:lista=mascotaRepositorio.findByNameAndEspecieAndClinica(nombre, especie, clinic, pagerequest);
		  			break;
		  	case 14:lista=mascotaRepositorio.findByNameAndUsuarioAndEspecie(nombre, usuario, especie, pagerequest);
					break;
		  	case 15:lista=mascotaRepositorio.findByNameAndUsuarioAndEspecieAndClinica(nombre, usuario, especie, clinic, pagerequest);
		  			break;
		  	default: lista=mascotaRepositorio.findAll(pagerequest);
  			
		  	}
		
				
		model.addAttribute("nombre", nombre);
		model.addAttribute("document", document);
		model.addAttribute("especie", especie);
		model.addAttribute("clinica", clinica);
		model.addAttribute("mascotas",lista);
		model.addAttribute("numPag", sigPag);
		
		return "mascotasbusqueda_template";
	}
	
	@GetMapping ("/busqueda_mascota")
	public String BusquedaProducto (Model model) {
		return "busquedamascota_template";
	}
	
	@GetMapping ("/registrar_mascota")
	public String registrarMascota (Model model) {
		
		return "clinicaAlta_template";
	}
	
	@GetMapping ("/mascota_alta")
	public String altaMascota(Model model, @RequestParam String ownerDoc, @RequestParam String name, @RequestParam String especie, @RequestParam String raza, @RequestParam String color) {
	
		Cliente user;

		user = (Cliente) usuarioRepositorio.findByDocument(ownerDoc);

		if (user!=null) {
			System.out.println ("entro al if");
			Mascota mascotaNueva = new Mascota(name, especie, raza, color/*,clinicaRepositorio.findByName("Las Aguilas").get(0)*/);
			mascotaNueva.setUsuario(user);
			//ver por que esto
			if (user.getClinica() != null) {
				System.out.println("Existe clinica del usuario");
				mascotaNueva.setClinica(user.getClinica().get(0));
			} else {
				mascotaNueva.setClinica(clinicaRepositorio.findByName("Las Aguilas")); 
				}
			mascotaRepositorio.save(mascotaNueva);
			model.addAttribute("mascota", true);
		
			return "registroexitoso_template";
		}
		System.out.println("No se encuentra el dueño");
		return "clinicaAlta_template";
	}
	
	@GetMapping ("/baja_mascota")
	public String bajaMascota(Model model, @RequestParam String id) {

		System.out.println(id);
		long longID=Long.parseLong(id);
		Optional<Mascota> optional=mascotaRepositorio.findById(longID);
		Mascota mascota= optional.get();
		mascotaRepositorio.delete(mascota);
		model.addAttribute("mascota", true);
		return "borradoexitoso_template";
	}
	
	@GetMapping("/modificar_mascota")
	public String ModificarProducto(Model model, @RequestParam String id) {
		System.out.println(id);
		long longID=Long.parseLong(id);
		Optional<Mascota> optional=mascotaRepositorio.findById(longID);
		Mascota mascota= optional.get();
		model.addAttribute("id", id);
		model.addAttribute("name", mascota.getName());
		model.addAttribute("especie", mascota.getEspecie());
		model.addAttribute("raza", mascota.getRaza());
		model.addAttribute("color", mascota.getColor());
		model.addAttribute("owner", mascota.getUsuario().getName());
		model.addAttribute("registro",mascota.getRegistro());
		
			return "modificarmascota_template";
	}
	@GetMapping("/cambiar_mascota")
	public String cambiarProducto(Model model, @RequestParam String id,@RequestParam String name,@RequestParam String especie,
			@RequestParam String raza,@RequestParam String color,@RequestParam String registro,@RequestParam String clinica) {
		long longID=Long.parseLong(id);
		Optional<Mascota> optional=mascotaRepositorio.findById(longID);
		Mascota mascota= optional.get();
		mascota.setName(name);
		mascota.setEspecie(especie);
		mascota.setRaza(raza);
		mascota.setColor(color); 
		if (!registro.equals("")) {
			String old_registro = mascota.getRegistro();
			mascota.setRegistro(old_registro+"["+clinica+"]: "+registro+"\n");
		}
		mascotaRepositorio.save(mascota);
		model.addAttribute("mascota", true);
			return "cambioexitoso_template";
	}
	
}
