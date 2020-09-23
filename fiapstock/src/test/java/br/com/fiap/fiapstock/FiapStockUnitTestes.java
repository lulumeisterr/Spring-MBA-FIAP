package br.com.fiap.fiapstock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.fiapstock.dto.StockDTO;
import br.com.fiap.fiapstock.model.Stock;
import br.com.fiap.fiapstock.repository.StockRepository;
import br.com.fiap.fiapstock.service.StockService;
import br.com.fiap.fiapstock.service.impl.StockImpl;

public class FiapStockUnitTestes {

	@Test
	public void findAllStocks() {
		
		// Preparacao	
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		
		// Simula oq volta do banco
		List<Stock> stockList = new ArrayList<>();
		Stock stock = new Stock();
		stock.setNome("Lucas");
		stock.setDescricao("Lucas Desc");
		
		stockList.add(stock);
			//Adicionando comportamento
			Mockito.when(stockRepository
					.buscaPorNome("Lucas")).thenReturn(stockList);
			
		// Execucao
		
		StockService stockService = 
				new StockImpl(null, null);
		
		List<StockDTO> stockDTOList = 
				stockService.findall("Lucas");
		
		// Verificacao
		
	}
	
}
