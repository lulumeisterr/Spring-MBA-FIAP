package br.com.fiap.fiapstock.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockDTO update(StockCreateUpdateDTO createUpdateDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
