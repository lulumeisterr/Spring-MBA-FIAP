package br.com.fiap.fiapstock.security;

import java.io.IOException;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Validar os tokens da requisicao
 * @author lucasrodriguesdonascimento
 *
 */
@Component
public class JwtFilter extends OncePerRequestFilter{

	private JwtUserDetails jwtUserDetails;
	private JwtTokenUtil jwtTokenUtil;
	
	public JwtFilter(JwtUserDetails jwtUserDetails , JwtTokenUtil jwtTokenUtil) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.jwtUserDetails = jwtUserDetails;
	}
	
	
	/**
	 * Busca a chave de autorizacao do header
	 * Apartir do rest e utilizando a camada do JPA
	 */
	@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeaderToken = request.getHeader("Authorization");
        String username = null;

        if (authorizationHeaderToken != null && authorizationHeaderToken.startsWith("Bearer ")) {
            try {
                username = jwtTokenUtil.getUsernameFromToken(authorizationHeaderToken);
            } catch (IllegalArgumentException illegal) {
                logger.info(illegal.getMessage());
            } catch (ExpiredJwtException expired) {
                logger.info(expired.getMessage());
            }
        } else {
            logger.warn("Token null ou fora do padrao Bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtUserDetails.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                    null,
                    userDetails.getAuthorities());// Role

            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

}
