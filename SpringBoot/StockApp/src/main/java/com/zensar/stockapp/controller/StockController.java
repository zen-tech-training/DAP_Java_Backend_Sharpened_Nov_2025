package com.zensar.stockapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.zensar.stockapp.dto.StockDto;
import com.zensar.stockapp.exception.InvalidStockIdException;
import com.zensar.stockapp.service.StockService;

@RestController
@RequestMapping("/zenstockapp")	//BASE URL of the APP
public class StockController {

	@Autowired
	StockService stockService;

	//Local exception handler
	@ExceptionHandler(value = {InvalidStockIdException.class})
	public ResponseEntity<Object> handleInvalidStockId(Exception ex, WebRequest request) throws Exception {
		System.out.println("StockController.handleInvalidStockId() is called");
		String clientMessage = ex.toString();
		String jsonMessage = "{\"error\": \"" + clientMessage + "\"}";
		return new ResponseEntity<Object>(jsonMessage, HttpStatus.CONFLICT);
	}	
	
	@GetMapping(value="/show")
	public ResponseEntity<String> getName(@RequestParam(name = "name", required = false)String stockName) {
		return new ResponseEntity<String>(stockName, HttpStatus.OK);
	}
	
	@GetMapping(value="/stock/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDto> getStockById(@PathVariable("id")int stockId) {
		return new ResponseEntity<StockDto>(stockService.getStockById(stockId), HttpStatus.OK);
	}
	
	@PostMapping(value="/stock", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockDto) { 
			//@RequestHeader("Authorization")String token) {
		//System.out.println("Authorization token: " + token);
		return new ResponseEntity<StockDto>(stockService.createStock(stockDto), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/stock/{id}", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDto> updateStock(@PathVariable("id")int stockId, @RequestBody StockDto stockDto) {
		return new ResponseEntity<StockDto>(stockService.updateStock(stockId, stockDto), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/stock/{id}")
	public ResponseEntity<Boolean> deleteStockById(@PathVariable("id")int stockId) {
		return new ResponseEntity<Boolean>(stockService.deleteStockById(stockId), HttpStatus.OK);
	}
	
	@GetMapping(value="/stocks", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDto>> getAllStocks() {
		return new ResponseEntity<List<StockDto>>(stockService.getAllStocks(), HttpStatus.OK);
	}
	
	@GetMapping(value="/stock/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDto>> getStocksByName(@PathVariable("name") String name) {
		return new ResponseEntity<List<StockDto>>(stockService.findByName(name), HttpStatus.OK);
	}

	@GetMapping(value="/stock/marketname/{marketname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StockDto> getStocksByMarketName(@PathVariable("marketname") String marketname) {
		return stockService.findByMarket(marketname);
	}

	@GetMapping(value="/stock/marketname/{marketname}/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StockDto> getStocksByNameAndMarketName(@PathVariable("marketname") String marketname, @PathVariable("name") String name) {
		return stockService.findByNameAndMarket(name, marketname);
	}

	@GetMapping(value="/stock/name/like/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StockDto> getStocksByNameLike(@PathVariable("name") String name) {
		return stockService.findByNameLike(name);
	}

	@GetMapping(value="/stock/name/sort/{sortType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StockDto> getStocksOrderByName(@PathVariable("sortType") String sortType) {
		return stockService.findByOrderByName(sortType);
	}

	@GetMapping(value="/stock/page/{startIndex}/{records}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StockDto> getStocksByPage(@PathVariable("startIndex") int startIndex, @PathVariable("records") int records) {
		return stockService.findByPage(startIndex, records);
	}
	
	@GetMapping(value="/stock/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDto>> getStocksBySearchText(@RequestParam(name="searchText", required=false)String searchText) {
		return new ResponseEntity<List<StockDto>>(stockService.getStocksBySearchText(searchText), HttpStatus.OK);
	}
	
}
