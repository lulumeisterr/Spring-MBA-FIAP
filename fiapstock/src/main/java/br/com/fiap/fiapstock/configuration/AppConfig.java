package br.com.fiap.fiapstock.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.fiapstock.service.StockPriceService;
import br.com.fiap.fiapstock.service.impl.StockPriceImpl;

/**
 * Spring procura por essas classes por @Configuration
 * @author lucasrodriguesdonascimento
 *
 */
@Configuration
public class AppConfig {
	

	/**
	 * Vai gerar uma instancia de alguma classe que implementa essa interface
	 * @return
	 */
	@Bean
	StockPriceService stockPriceCheck() {
		return new StockPriceImpl();
	}


}
