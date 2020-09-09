package br.com.fiap.fiapstock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.fiapstock.dto.StockDTO;
import br.com.fiap.fiapstock.dto.StockCreateUpdateDTO;
import br.com.fiap.fiapstock.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * 
 * RestController vs Controller
 * 
 * Controller e uma especializacao da anotacao RestController
 * 
 * RestController -> Permite a serializacao em json,xml ou qualquer outro formato e nao ser processado como
 * template html e jogado na resposta. 
 * 
 * Controller -> Precisamos anotar o metodo com a anotacao @ResponseBody para serializar os dados na resposta e para
 * nao ficar utilizando essa anotacao nas nossas apis restfuls nos utilizamos a RestController apartir da versao 4 do spring
 * que elimina a necessidade de nao usar a anotacao @ResponseBody
 * 
 * 
 * - Spring utiliza o Jackson para realizar o bind de objeto json para DTO java.
 * 
 * @author lucasrodriguesdonascimento
 * 
 * Actuator -> Serve para monitorar a aplicacao
 *
 */
@RestController
@RequestMapping("stocks")
public class EstoqueController {
	
//	@Autowired
	private StockService stockService;
	
	/**
	 * Spring Teams Recommend, Sempre usar injecao de dependencia baseada nos seus beans
	 * @param service
	 */
	public EstoqueController(StockService service) {
		this.stockService = service;
	}

	/**
	 * - Querys Params Opcionais realizado para filtrar e ordenar
	 * @return
	 */
	
	@GetMapping
	@Operation(description = "Listagem de acoes por nome")	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Listagem OK"),
			@ApiResponse(responseCode = "400" ,description = "stocks.not.found")
	})
//	List<EstockDTO> findAll(
//			@RequestParam(required = false,value = "nome") String nome){
//		return stockDTOList.stream()
//				.filter(EstockDTO::getAtivo)
//				.filter(estoqueDTO -> nome == null || estoqueDTO.getNome()
//				.contains(nome)).collect(Collectors.toList());
//	}
	
	List<StockDTO> findAll(
			@RequestParam(required = false,value = "nome") String nome){
		return stockService.findall(nome);
	}

	/**
	 * - Path Parameter Filtrando por campos unicos
	 * @param id
	 * @return
	 */
//	@GetMapping("{id}")
//	EstockDTO getById(@PathVariable Long id) {
//		return stockDTOList.stream().filter(stockDTO -> stockDTO.getId()
//				.equals(id)).findFirst()
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//	}
	@GetMapping("{id}")
	StockDTO getById(@PathVariable Long id) {
		return stockService.findById(id);
	}

	/**
	 * Body eh enviando no corpo da requisicao Apenas Para
	 * POST , PUT , PATCH
	 * 
	 * Nao expor a entidade do banco de dados , Pode seguir com o
	 * padrao dto ou presenters.
	 * 
	 * 
	 * Se nos utilizar DTO para tudo podemos nos confudir oque
	 * esta entrando e saindo do nosso backend por isso utilizamos
	 * o Presenter para saida de informacao e Request para entrar
	 */
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	EstockDTO create(@RequestBody StockCreateUpdateDTO stockCreateUpdateDTO) {
//		
//		EstockDTO stockDTO = new EstockDTO(stockDTOList.size()+1L,stockCreateUpdateDTO.getNome(),stockCreateUpdateDTO.getDescricao(),stockCreateUpdateDTO.getValor(),true);
//		stockDTOList.add(stockDTO);
//		return stockDTO;
//	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	StockDTO create(@RequestBody StockCreateUpdateDTO stockCreateUpdateDTO) {
		return stockService.create(stockCreateUpdateDTO);
	}
	
	/**
	 * Atualizacao
	 * @param stockCreateUpdateDTO
	 * @param id
	 * @return
	 */
//	@PutMapping("{id}")
//	EstockDTO update(@RequestBody StockCreateUpdateDTO stockCreateUpdateDTO, @PathVariable Long id) {
//		
//		EstockDTO stockDTO = new EstockDTO(
//				stockDTOList.size()+1L,
//				stockCreateUpdateDTO.getNome(),
//				stockCreateUpdateDTO.getDescricao(),
//				stockCreateUpdateDTO.getValor(),stockCreateUpdateDTO.getAtivo());
//		
//		return stockDTO;
//		
//	}
	
	@PutMapping("{id}")
	StockDTO update(@RequestBody StockCreateUpdateDTO stockCreateUpdateDTO, @PathVariable Long id) {
		return stockService.update(stockCreateUpdateDTO, id);
		
	}
	
	/**
	 * Delete
	 * @param id
	 */
//	@DeleteMapping("{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	void delete(@PathVariable Long id){
//		EstockDTO stockDTO = getById(id);
//		stockDTO.setAtivo(false);
//	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id){
		StockDTO stockDTO = getById(id);
		stockDTO.setAtivo(false);
	}
	
	
//	/**
//	 * Mock List
//	 */
//	public EstoqueController() {
//
//		EstockDTO stock1 = new EstockDTO(1L,"Lucas","Desc",true,BigDecimal.valueOf(100.0));
//		EstockDTO stock2 = new EstockDTO(2L,"Jose","Desc",false,BigDecimal.valueOf(200.0));
//
//		stockDTOList.add(stock1);
//		stockDTOList.add(stock2);
//
//	}

}
