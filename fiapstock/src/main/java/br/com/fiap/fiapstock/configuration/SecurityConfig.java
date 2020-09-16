package br.com.fiap.fiapstock.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.fiap.fiapstock.security.JwtAuthenticationEntrypoint;
import br.com.fiap.fiapstock.security.JwtFilter;
import br.com.fiap.fiapstock.security.JwtUserDetails;

/**
 * JWT funciona como um guia de implementacao
 * @author lucasrodriguesdonascimento
 *
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtAuthenticationEntrypoint authenticationEntrypoint ;
	@Autowired
	private JwtUserDetails jwtUserDetails;
	@Autowired
	private JwtFilter jwtFilter;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public SecurityConfig(JwtAuthenticationEntrypoint authenticationEntrypoint, JwtUserDetails jwtUserDetails,
			JwtFilter jwtFilter, PasswordEncoder passwordEncoder) {
		super();
		this.authenticationEntrypoint = authenticationEntrypoint;
		this.jwtUserDetails = jwtUserDetails;
		this.jwtFilter = jwtFilter;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
	
	/**
	 * Configurando os usuarios
	 * noop para remover a criptografia
	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("admin").password("{noop}fiapadmin").roles("ADMIN","USER")
//		.and()
//		.withUser("user").password("{noop}fiapUser").roles("USER");
//	}
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(jwtUserDetails)
	                .passwordEncoder(passwordEncoder);
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
                .antMatchers("/users/**").permitAll()
                .anyRequest().authenticated()
                .and()

                .exceptionHandling().authenticationEntryPoint(authenticationEntrypoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .csrf().disable()
                .formLogin().disable();
    }
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic()
//		.and()
//		.authorizeRequests()
//		.antMatchers(HttpMethod.POST,"/stocks").hasRole("ADMIN")
//		.anyRequest().authenticated()
//		.and()
//		.csrf().disable() //Quando alguem tenta acessar vindo de outro site , barra no postman por isso desativamos
//		.formLogin().disable()
//		;
//	}
	
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
