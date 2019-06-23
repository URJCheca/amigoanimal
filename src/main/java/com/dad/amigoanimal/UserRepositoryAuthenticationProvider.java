package com.dad.amigoanimal;

import com.dad.amigoanimal.SecurityConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private ClienteRepository clienteRepositorio;
	@Autowired
	private TrabajadorRepository trabajadorRepositorio;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// System.out.println("-- VAMOS A VERIFICAR ESTE USUARIO authentication provider --");
		String rol=null;
		String login=null;
		String passHash=null;
		
		Optional<Cliente> usuarioC = clienteRepositorio.findByLogin(authentication.getName());
		if (usuarioC.isPresent()) {
			Cliente cliente= usuarioC.get();
			rol=cliente.getRol();
			login=cliente.getLogin();
			passHash=cliente.getPasswordHash();
		}else {
			Optional<Trabajador> usuarioT =  trabajadorRepositorio.findByLogin(authentication.getName());
			if (usuarioT.isPresent()) {
			Trabajador trabajador= usuarioT.get();
			rol=trabajador.getRol();
			login=trabajador.getLogin();
			passHash=trabajador.getPasswordHash();
			}
		}
		String password = (String) authentication.getCredentials();
		if(!new BCryptPasswordEncoder().matches(password, passHash)) {
			throw new BadCredentialsException("Wrong password");
		}
		
		
		List<GrantedAuthority> rolList = new ArrayList<>();
		rolList.add(new SimpleGrantedAuthority(rol));
		
		return (Authentication) new UsernamePasswordAuthenticationToken(login, password, rolList);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	
}