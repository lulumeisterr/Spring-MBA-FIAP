package br.com.fiap.fiapstock.controller;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * BeanConditinal
 * 
 * Se ele contrar essa properites fiap.debug ai ele instancia esse controller
 * @author lucasrodriguesdonascimento
 * 
 * endpoint -> debug?property=fiap.debug
 *
 */
 
@RestController
@RequestMapping("debug")
@ConditionalOnProperty(value = "fiap.debug" , havingValue = "true")
public class DebuggerController {

	/**
	 * Classe para ler properties
	 * @param properties
	 * @return
	 */

	private Environment env;

	/**
	 * Injecao de depedencia
	 * @param env
	 */
	public DebuggerController(Environment env) {
		this.env = env;
	}

	@GetMapping
	String getProperty(@RequestParam("property") String properties) {
		return env.getProperty(properties);
	}

}
