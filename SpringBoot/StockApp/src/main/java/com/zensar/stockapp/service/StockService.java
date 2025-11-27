package com.zensar.stockapp.service;

import java.util.List;

import com.zensar.stockapp.dto.StockDto;

public interface StockService {
	public List<StockDto> getAllStocks();
	public StockDto getStockById(int stockId);
	public StockDto createStock(StockDto stockDto);
	public StockDto updateStock(int stockId, StockDto stockDto);
	public boolean deleteStockById(int stockId);
	
	List<StockDto> findByMarket(String market);
	List<StockDto> findByName(String name);
	List<StockDto> findByNameAndMarket(String name, String market);
	List<StockDto> findByNameLike(String name);	
	List<StockDto> findByOrderByName(String sortType);
	List<StockDto> findByPage(int startIndex, int records);
	List<StockDto> getStocksBySearchText(String searchText);
	
}
