package com.dad.amigoanimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class CSRFHandlerConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CSRFHandlerInterceptor());
	}
}

class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    if (auth != null){  
		    	if (auth.getPrincipal() == "anonymousUser") {
			    	modelAndView.addObject("name", "Invitado");
		    	} else {
			    	modelAndView.addObject("name", auth.getPrincipal());
			    }
				modelAndView.addObject("admin", request.isUserInRole("ROLE_ADMIN"));
		    } else {
		    	modelAndView.addObject("name", "Invitado");
				modelAndView.addObject("admin", false);
		    }
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			modelAndView.addObject("token", token.getToken());
		}
	}
	
}