package br.com.fiap.fiapstock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.fiapstock.dto.AuthDTO;
import br.com.fiap.fiapstock.dto.JwtDTO;
import br.com.fiap.fiapstock.dto.UserCreateDTO;
import br.com.fiap.fiapstock.dto.UserDTO;
import br.com.fiap.fiapstock.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService){
		this.userService = userService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createUser(@RequestBody UserCreateDTO userCreateDTO){
		return userService.createUser(userCreateDTO);
	}

	@PostMapping("login")
	public JwtDTO login(@RequestBody AuthDTO authDTO){
		return userService.login(authDTO);
	}
}