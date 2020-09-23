package br.com.fiap.fiapstock;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.fiap.fiapstock.dto.UserCreateDTO;

/**
 * Teste integrado
 * @author lucasrodriguesdonascimento
 *
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:database")
class FiapstockApplicationTests {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		
		//preparacao
		
		UserCreateDTO userCreateDTO = new UserCreateDTO();
		
		userCreateDTO.setUsername("Lucas");
		userCreateDTO.setPassword("123456");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		try {
			json = ow.writeValueAsString(userCreateDTO);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
		// execucao e verificacao
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.post("/users")
					.content(json)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(MockMvcResultMatchers.status().isCreated())
					.andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("Lucas")));
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
