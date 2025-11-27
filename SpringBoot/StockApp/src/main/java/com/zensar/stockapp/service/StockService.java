package com.zensar.stockapp.service;

import java.util.List;

import com.zensar.stockapp.dto.StockDto;

public interface StockService {
	public List<StockDto> getAllStocks();
	public StockDto getStockById(int stockId);
	public StockDto createStock(StockDto stockDto);
	public StockDto updateStock(int stockId, StockDto stockDto);
	public boolean deleteStockById(int stockId);
}
