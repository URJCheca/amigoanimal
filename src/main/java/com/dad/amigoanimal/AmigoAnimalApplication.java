package com.dad.amigoanimal;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@EnableCaching
@SpringBootApplication
public class AmigoAnimalApplication {

	private static final Log LOG = LogFactory.getLog(AmigoAnimalApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AmigoAnimalApplication.class, args);
		
		ClienteSocket.enviarSocket();
	}
	
	@Bean
	public CacheManager cacheManager() {
		LOG.info("Activating cache...");
		return new ConcurrentMapCacheManager("productos");
}

}

