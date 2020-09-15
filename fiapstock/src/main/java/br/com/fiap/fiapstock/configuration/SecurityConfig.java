package br.com.fiap.fiapstock.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	/**
	 * Configurando os usuarios
	 * noop para remover a criptografia
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin").password("{noop}fiapadmin").roles("ADMIN","USER")
		.and()
		.withUser("user").password("{noop}fiapUser").roles("USER");
	}
	
	/**
	 * Configurando a autentificacao
	 * Todos os usuarios so podem realizar leitura apenas os admir consegue tudo
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/stocks").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.csrf().disable() //Quando alguem tenta acessar vindo de outro site , barra no postman por isso desativamos
		.formLogin().disable()
		;
	}
	
	/**
	 * Habilitando endpoints para publico
	 * 
	 */
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers(
				"/v3/api-docs",
				"/configuration/ui",
				"/swagger-resources/***",
				"/configuration/security", 
				"/swagger-ui/**",
				"/webjars/**");
	}
}
