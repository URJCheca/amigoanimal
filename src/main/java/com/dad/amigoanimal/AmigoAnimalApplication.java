package com.dad.amigoanimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmigoAnimalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmigoAnimalApplication.class, args);
		
		ClienteSocket.enviarSocket();
	}

}

