package com.zensar.stockapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zensar.stockapp.dto.StockDto;
import com.zensar.stockapp.entity.StockEntity;
import com.zensar.stockapp.exception.InvalidStockIdException;
import com.zensar.stockapp.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	private List<StockDto> getStockDtoList(List<StockEntity> stockEntityList) {
		List<StockDto> stockDtoList = new ArrayList<StockDto>();
		TypeMap<StockEntity, StockDto> typeMap = 
				modelMapper.typeMap(StockEntity.class, StockDto.class);
		typeMap.addMapping((stockEntity)->stockEntity.getMarket(), StockDto::setMarketName);
		for(StockEntity stockEntity: stockEntityList) {
			StockDto stockDto = modelMapper.map(stockEntity, StockDto.class);
			stockDtoList.add(stockDto);
		}
		return stockDtoList;
	}
	private StockDto getDto(StockEntity stockEntity) {
		StockDto stockDto = modelMapper.map(stockEntity, StockDto.class);
		return stockDto;
	}
	private StockEntity getEntity(StockDto stockDto) {
		TypeMap<StockDto, StockEntity> typeMap = 
				modelMapper.typeMap(StockDto.class, StockEntity.class);
		typeMap.addMapping((dto)->dto.getMarketName(), StockEntity::setMarket);
		StockEntity stockEntity = modelMapper.map(stockDto, StockEntity.class);
		return stockEntity;
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
			stockEntity.setMarket(stockDto.getMarketName());
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
	@Override
	public List<StockDto> findByMarket(String market) {
		List<StockEntity> stockEntityList = stockRepository.findByMarket(market);
		return getStockDtoList(stockEntityList);
	}
	@Override
	public List<StockDto> findByName(String name) {
		List<StockEntity> stockEntityList = stockRepository.findByName(name);
		return getStockDtoList(stockEntityList);
	}
	@Override
	public List<StockDto> findByNameAndMarket(String name, String market) {
		List<StockEntity> stockEntityList = stockRepository.findByNameAndMarket(name, market);
		return getStockDtoList(stockEntityList);
	}
	@Override
	public List<StockDto> findByNameLike(String name) {
		List<StockEntity> stockEntityList = stockRepository.findByNameContaining(name);
		return getStockDtoList(stockEntityList);
	}
	@Override
	public List<StockDto> findByOrderByName(String sortType) {
		List<StockEntity> stockEntityList = null;
		if(sortType!=null && sortType.equalsIgnoreCase("ASC"))
			stockEntityList =  stockRepository.findByOrderByName();
		else
			stockEntityList =  stockRepository.findByOrderByNameDesc();
		return getStockDtoList(stockEntityList);
	}
	@Override
	public List<StockDto> findByPage(int startIndex, int records) {
		Pageable pageable = PageRequest.of(startIndex, records);
		
		Page<StockEntity> page = stockRepository.findAll(pageable);
		List<StockEntity> stockEntityList = page.getContent();
		return getStockDtoList(stockEntityList);
	}
	@Override
	public List<StockDto> getStocksBySearchText(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}
}
