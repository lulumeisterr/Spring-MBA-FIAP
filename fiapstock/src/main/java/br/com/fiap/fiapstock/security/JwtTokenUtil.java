package br.com.fiap.fiapstock.security;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Classe responsavel por gerenciar os metodos de autentificacao do JWT
 * e tambem para criar e ler usuarios do token
 * @author lucasrodriguesdonascimento
 *
 */

@Component
public class JwtTokenUtil {
	
	/**
	 * Chave para propria aplicacao
	 */
	@Value("${jwt.secret}")
	private String secret;
	
	/**
	 * Periodo de duracao do token
	 */
	@Value("${jwt.expire}")
	private int expire;
	
	
	/**
	 * Metodo para gerar o token
	 * @param username
	 * @return
	 */
	public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>(); // Roles
        Date dataCriacao = getFromLocalDate(LocalDateTime.now());
        Date dataExpiracao = getFromLocalDate(LocalDateTime.now().plusMinutes(expire));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(dataCriacao)
                .setExpiration(dataExpiracao)
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


	/**
	 * Abrir o token e obter as info
	 * @param now
	 * @return
	 */
	
	public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
        return claims.getSubject();
    }
	
	
	private Date getFromLocalDate(LocalDateTime now) {
		return Date.from(now.toInstant(OffsetDateTime.now().getOffset()));
	}
	
	

}
