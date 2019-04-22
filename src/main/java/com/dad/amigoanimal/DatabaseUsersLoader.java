package com.dad.amigoanimal;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {
	@Autowired
	private UserBaseRepository userRepository;
	@PostConstruct
	private void initDatabase() {

		Cliente cliente = new Cliente("1","1","123","11111111","1@hotmail.com","ROLE_ADMIN");
		Cliente cliente2 = new Cliente("Jose","Jose","Jose123","12312312","jose@hotmail.com","ROLE_ADMIN");
		userRepository.save(cliente);
		userRepository.save(cliente2);
		//cliente.carrito.addProducto();
		/*userRepository.save(
				new Usuario("user", "Jose", "pass", "123", "jose@gmail.com", "ROLE_USER"));
		userRepository.save(
				new Usuario("admin", "Alex", "adminpass", "1234", "alex@gmail.com", "ROLE_ADMIN"));
		*/
 }
}