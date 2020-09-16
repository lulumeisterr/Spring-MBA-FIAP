package br.com.fiap.fiapstock.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.fiap.fiapstock.model.User;
import br.com.fiap.fiapstock.repository.UserRepository;

/**
 * Trata dados do usuario , onde vamos buscar os dados do usuario , entao vamos
 * connectar o spring security com o jpa disponibilizando a interface apartir da sobrescrita de metodo
 * @author lucasrodriguesdonascimento
 *
 */

@Component
public class JwtUserDetails implements UserDetailsService{

	private UserRepository userRepository;
	
	public JwtUserDetails(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * Permite que nos connectamos com o spring data para realizar a consulta
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findFirstByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found !!!"));
		
		return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                new ArrayList<>() // Roles
        );
	}

}
