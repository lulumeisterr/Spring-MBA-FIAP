package br.com.fiap.fiapstock.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Classe responsavel por tratar Error 401
 * @author lucasrodriguesdonascimento
 *
 *
 */
@Component
public class JwtAuthenticationEntrypoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"token.invalido");
		
	}

}
