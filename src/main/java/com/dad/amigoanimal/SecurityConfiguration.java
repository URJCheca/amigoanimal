package com.dad.amigoanimal;

import com.dad.amigoanimal.UserRepositoryAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure (HttpSecurity http) throws Exception{
		//http.anonymous().disable();
		//Publico
		http.authorizeRequests().antMatchers("/css/**", "/images/**").permitAll();
		
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/greeting").permitAll();
		http.authorizeRequests().antMatchers("/signin").permitAll();
		http.authorizeRequests().antMatchers("/signin_fail").permitAll();
		http.authorizeRequests().antMatchers("/signed").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		
		//privado especifico
		//Paginas de modificacion y borrado de cosas
		//http.authorizeRequests().antMatchers("/catalogo").hasAnyRole("USER");
		//http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/ver_carrito").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/aniadir_carrito").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/eliminar_carrito").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/enviar_carrito").hasAnyRole("USER");
		
		http.authorizeRequests().antMatchers("/clinica").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/alta_producto").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/registrar_producto").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/borrar_producto").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/modificar_producto").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/cambiar_producto").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/administrar").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/cambiar_rol").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/borrar_usuario").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/registro_mascotas").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/busqueda_avanzada_mascota").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/busqueda_mascota").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/registrar_mascota").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/mascota_alta").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/baja_mascota").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/modificar_mascota").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/cambiar_mascota").hasAnyRole("ADMIN");
		
		//Privado
		//http.authorizeRequests().anyRequest().authenticated();
				
		
		//Login
		http.formLogin().loginPage("/signin");
		http.formLogin().usernameParameter("user");
		http.formLogin().passwordParameter("pass");
		http.formLogin().defaultSuccessUrl("/signed");
		http.formLogin().failureUrl("/signin_fail");
		
				
				
		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/signin");
				
		//CSRF deshabilitado
		//http.csrf().disable();
	}
	
	@Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	 // User
		//auth.inMemoryAuthentication().withUser("jose").password("{noop}123").roles("USER");
		//auth.inMemoryAuthentication().withUser("admin").password("{noop}adminpass").roles("USER", "ADMIN");
		auth.authenticationProvider(authenticationProvider);
	}
	
	/*@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}*/
}
