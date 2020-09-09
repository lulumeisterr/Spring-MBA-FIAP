package br.com.fiap.fiapstock.service;

import br.com.fiap.fiapstock.dto.StockDTO;

public interface StockPriceService {
	
	boolean checkPrice(StockDTO stockDTO);

}
