package br.com.fiap.fiapstock.service;

import java.util.List;

import br.com.fiap.fiapstock.dto.StockDTO;
import br.com.fiap.fiapstock.dto.StockCreateUpdateDTO;

public interface StockService {
	 
	List<StockDTO> findall(String search);
	StockDTO findById(Long id);
	StockDTO create(StockCreateUpdateDTO createUpdateDTO);
	StockDTO update (StockCreateUpdateDTO createUpdateDTO, long id);

}
