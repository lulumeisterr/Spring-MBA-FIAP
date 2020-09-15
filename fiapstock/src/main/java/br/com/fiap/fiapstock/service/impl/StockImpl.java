package br.com.fiap.fiapstock.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.fiapstock.dto.StockCreateUpdateDTO;
import br.com.fiap.fiapstock.dto.StockDTO;
import br.com.fiap.fiapstock.model.Stock;
import br.com.fiap.fiapstock.repository.StockRepository;
import br.com.fiap.fiapstock.service.StockPriceService;
import br.com.fiap.fiapstock.service.StockService;

/**
 * @Service -> Trata regra de negocios (Indicativo que trabalhamos em regra de negocio) 
 * 
 * -> Sempre que vc estive uma service deve criar-se uma interface
 * 
 * -> Camada controller so vai ter regras de interfaces
 * 
 * @author lucasrodriguesdonascimento
 *
 */
@Service
public class StockImpl implements StockService{

//	
//	private List<EstockDTO> stockDTOList = new ArrayList<>();
	
	//Injecao de dependencia
	private StockPriceService stockPriceService;
	private StockRepository stockRepository;
	
	public StockImpl(StockPriceService stockPriceService , StockRepository stockRepository){
		this.stockPriceService = stockPriceService;
		this.stockRepository = stockRepository;
		
	}
	
	@Override
	public List<StockDTO> findall(String search) {
		//return stockRepository.findAllByNomeContainingAndAtivoIsTrue(search)
		return stockRepository.buscaPorNome(search)
				.stream()
				//.filter(stock -> search == null || stock.getNome().contains(search))
				.map(StockDTO::new)
				.collect(Collectors.toList());
	}

	@Override
	public StockDTO create(StockCreateUpdateDTO createUpdateDTO) {
		Stock stock = new Stock(createUpdateDTO);
		Stock savedStock = stockRepository.save(stock);
		return new StockDTO(savedStock);
	}
	
	@Override
	public StockDTO findById(Long id) {
		Stock stock = getStockById(id);
		return new StockDTO(stock);
	}


	@Override
	public StockDTO update(StockCreateUpdateDTO createUpdateDTO, long id) {		
		Stock stock = getStockById(id);

		stock.setNome(createUpdateDTO.getNome());
		stock.setDescricao(createUpdateDTO.getDescricao());
		stock.setValor(createUpdateDTO.getValor());
		Stock savedStock = stockRepository.save(stock);		
		return new StockDTO(savedStock);
	}
	

	@Override
	public void delete(Long id) {
		Stock stock = getStockById(id);
		stock.setAtivo(false);
		stockRepository.save(stock);
		
	}
	
	/**
	 * Trata o findById e devolve um erro caso ocorra algo
	 * @param id
	 * @return
	 */
	private Stock getStockById(Long id) {
		return stockRepository.findByIdAndAtivoIsTrue(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}



}
