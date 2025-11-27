package com.zensar.stockapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.stockapp.dto.StockDto;
import com.zensar.stockapp.entity.StockEntity;
import com.zensar.stockapp.exception.InvalidStockIdException;
import com.zensar.stockapp.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;
	
	private List<StockDto> getStockDtoList(List<StockEntity> stockEntityList) {
		List<StockDto> stockDtoList = new ArrayList<StockDto>();
		for(StockEntity stockEntity: stockEntityList) {
			stockDtoList.add(new StockDto(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarket(), 
					stockEntity.getPrice()));
		}
		return stockDtoList;
	}
	private StockDto getDto(StockEntity stockEntity) {
		return new StockDto(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarket(), 
				stockEntity.getPrice());
	}
	private StockEntity getEntity(StockDto stockDto) {
		return new StockEntity(stockDto.getName(), stockDto.getMarket(), 
				stockDto.getPrice());
	}
	@Override
	public List<StockDto> getAllStocks() {
		List<StockEntity> stockEntityList = stockRepository.findAll();
		return getStockDtoList(stockEntityList);
	}

	@Override
	public StockDto getStockById(int stockId) {
		Optional<StockEntity> opStockEntity = stockRepository.findById(stockId);
		if(opStockEntity.isPresent()) { //stockId is correct
			StockEntity stockEntity = opStockEntity.get();
			return getDto(stockEntity);
		}
		throw new InvalidStockIdException(""+stockId);
	}
	
	@Override
	public StockDto createStock(StockDto stockDto) {
		StockEntity stockEntity = getEntity(stockDto);
		stockEntity = stockRepository.save(stockEntity);
		return getDto(stockEntity);
	}
	@Override
	public StockDto updateStock(int stockId, StockDto stockDto) {
		Optional<StockEntity> opStockEntity = stockRepository.findById(stockId);
		if(opStockEntity.isPresent()) { //stockId is correct
			StockEntity stockEntity = opStockEntity.get();
			stockEntity.setName(stockDto.getName());
			stockEntity.setMarket(stockDto.getMarket());
			stockEntity.setPrice(stockDto.getPrice());
			stockEntity = stockRepository.save(stockEntity);
			return getDto(stockEntity);
		}
		throw new InvalidStockIdException(""+stockId);
	}
	@Override
	public boolean deleteStockById(int stockId) {
		Optional<StockEntity> opStockEntity = stockRepository.findById(stockId);
		if(opStockEntity.isPresent()) { //stockId is correct
			stockRepository.deleteById(stockId);
			return true;
		}
		throw new InvalidStockIdException(""+stockId);
	}
	/*
	private static List<StockDto> stocks = new ArrayList<>();
	static {
		stocks.add(new StockDto(1, "Reliance", "BSE", 2500));
		stocks.add(new StockDto(2, "IBM", "NSE", 9000));
		stocks.add(new StockDto(3, "Zensar", "BSE", 11000));
	}
	*/
}
