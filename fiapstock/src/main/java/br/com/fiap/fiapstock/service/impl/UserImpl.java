package br.com.fiap.fiapstock.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.fiapstock.dto.AuthDTO;
import br.com.fiap.fiapstock.dto.JwtDTO;
import br.com.fiap.fiapstock.dto.UserCreateDTO;
import br.com.fiap.fiapstock.dto.UserDTO;
import br.com.fiap.fiapstock.model.User;
import br.com.fiap.fiapstock.repository.UserRepository;
import br.com.fiap.fiapstock.security.JwtTokenUtil;
import br.com.fiap.fiapstock.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * Responsavel por autentificar e logar o usuario
 * @author lucasrodriguesdonascimento
 *
 *
 */


@Service
public class UserImpl implements UserService{
	
	private JwtTokenUtil jwtTokenUtil;
	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	
	public UserImpl(JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO createUser(@RequestBody UserCreateDTO userCreateDTO) {
		
		User user = new User();
		
		user.setUserName(userCreateDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

		User savedUser = userRepository.save(user);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(savedUser.getId());
		userDTO.setUsername(savedUser.getUserName());
		
		return userDTO;
	}
	

	@Override
	public JwtDTO login(@RequestBody AuthDTO authDTO) {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
		}catch(AuthenticationException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
		}
		
		String token = jwtTokenUtil.generateToken(authDTO.getUsername());
		
		JwtDTO jwtDTO = new JwtDTO();
		jwtDTO.setToken(token);
		
		return jwtDTO;
	}
	

}
