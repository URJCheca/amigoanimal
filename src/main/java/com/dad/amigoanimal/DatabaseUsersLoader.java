package com.dad.amigoanimal;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {
	//@Autowired
	//private UserBaseRepository userRepository;
	@Autowired
	private ProductoRepository productoRepositorio;
	@Autowired
	private TrabajadorRepository trabajadorRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ClinicaRepository clinicaRepositorio;
	@Autowired
	private MascotaRepository mascotaRepositorio;
	@PostConstruct
	private void initDatabase() {

		Trabajador cliente1 = new Trabajador("1","1","123","11111111","1@hotmail.com","ROLE_ADMIN");
		Trabajador cliente2 = new Trabajador("Jose","Jose","Jose123","12312312","jose@hotmail.com","ROLE_ADMIN");
		Cliente cliente3 =new Cliente("alex","alex","alex123","46789143l","alex@hotmail.com","ROLE_USER");
		Cliente cliente4 =new Cliente("manu","manu","manu123","55098788p","manu@gmail.com","ROLE_USER");
		Cliente cliente5 =new Cliente("pepe","pepe","pepe123","22750912s","pepe@gmail.com","ROLE_USER");
		
		
		Mascota mascota1=new Mascota("Congo","Loro","Yaco","Gris");
		Mascota mascota2=new Mascota("Fluffy","perro","labrador","canela");
		Mascota mascota3=new Mascota("Miaustache","gato","persa","gris");
		Mascota mascota4=new Mascota("Calcetines","gato","europeo","negro");
		
		Clinica clinica1 = new Clinica("Las_Aguilas",3);
		Clinica clinica2 = new Clinica("Ballesteros",4);
		
		cliente1.setClinica(clinica1);
		cliente4.setClinica(clinica2);
		cliente3.setClinica(clinica1);
		cliente2.setClinica(clinica1);
		cliente5.setClinica(clinica2);
		
		clinicaRepositorio.save(clinica1);
		clinicaRepositorio.save(clinica2);
		
		trabajadorRepository.save(cliente1);
		trabajadorRepository.save(cliente2);
		clienteRepository.save(cliente3);
		clienteRepository.save(cliente4);
		clienteRepository.save(cliente5);
		
		
		
		mascota1.setUsuario(cliente5);
		mascota2.setUsuario(cliente4);
		mascota3.setUsuario(cliente4);
		mascota4.setUsuario(cliente3);
		
		mascota1.setClinica(clinica1);
		mascota2.setClinica(clinica1);
		mascota3.setClinica(clinica1);
		mascota4.setClinica(clinica2);
		
		/*cliente1.addMascota(mascota1);;
		cliente2.addMascota(mascota2);
		cliente3.addMascota(mascota3);
		cliente3.addMascota(mascota4);*/
				
		
		mascotaRepositorio.save(mascota1);
		mascotaRepositorio.save(mascota2);
		mascotaRepositorio.save(mascota3);
		mascotaRepositorio.save(mascota4);
		
		
		
		productoRepositorio.save(new Producto("Catzilla", 14, "Comida para gatos de alta calidad. Sabor pollo y verduras", "Alimentacion", 30));
		productoRepositorio.save(new Producto("Transportin grande", 20, "Transportin de grandes dimensiones para perros", "Viaje", 5));
		productoRepositorio.save(new Producto("Bun bunny", 5, "Complemento alimenticio para conejos", "Alimentacion",20));
		productoRepositorio.save(new Producto("Arenero", 10, "Arenero para gatos", "Higiene", 15));
		productoRepositorio.save(new Producto("Anti-lombrices", 8, "Pastillas anti parasitarias para perros y gatos", "Salud", 50));
		productoRepositorio.save(new Producto("Soga mordedora", 8, "Juguete para perros hecho de cuerda resistente para jugar al tira y afloja", "Juguetes", 10));
		productoRepositorio.save(new Producto("Kit Correa externsible", 13, "Correa extensible hasta 4 metros con collar incluido", "Accesorios", 7));
		productoRepositorio.save(new Producto("Champu para perros", 7, "Champu especialmente diseñado para perros. Deja el pelo suave y brillante", "Higiene", 12));
		/*Usuario user = new Usuario("123","123","123123","123123123","123@gmail.com",3);		
		
		user.setClinica(clinicaRepositorio.findByName("Las Aguilas").get(0));
		usuarioRepositorio.save(user);*/
		//cliente.carrito.addProducto();
		/*userRepository.save(
				new Usuario("user", "Jose", "pass", "123", "jose@gmail.com", "ROLE_USER"));
		userRepository.save(
				new Usuario("admin", "Alex", "adminpass", "1234", "alex@gmail.com", "ROLE_ADMIN"));
		*/
 }
}