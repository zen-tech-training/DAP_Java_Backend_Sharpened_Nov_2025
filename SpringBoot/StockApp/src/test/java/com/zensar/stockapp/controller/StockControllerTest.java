package com.zensar.stockapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.zensar.stockapp.dto.StockDto;
import com.zensar.stockapp.service.StockService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(StockController.class)
public class StockControllerTest {

	@MockitoBean
	StockService stockService;
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void testCreateStockFailure() throws Exception {
		StockDto stockDto = new StockDto(3, "Accenture", "NSE", 6000);
		when(stockService.createStock(stockDto)).thenReturn(stockDto);
		
		mockMvc.perform(post("http://localhost:8080/zenstockapp/stocks")
							.contentType("application/json")
							.content(objectMapper.writeValueAsString(stockDto)))
				.andExpect(status().isMethodNotAllowed());
	}
	//@Test
	public void testCreateStock() throws Exception {
		StockDto stockDto = new StockDto(3, "Accenture", "NSE", 6000);
		when(stockService.createStock(stockDto)).thenReturn(stockDto);
		
		MvcResult result = mockMvc.perform(post("http://localhost:8080/zenstockapp/stock")
							.contentType("application/json")
							.content(objectMapper.writeValueAsString(stockDto)))
				.andExpect(status().isCreated())
				.andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals(response.contains("Accenture"), true);
	}
	
	@Test
	public void testGetAllStocks() throws Exception {
		List<StockDto> stockDtoList = new ArrayList<>();
		stockDtoList.add(new StockDto(1, "Infosys", "BSE", 2000));
		stockDtoList.add(new StockDto(2, "Zensar", "NSE", 8000));
		when(stockService.getAllStocks()).thenReturn(stockDtoList);
		
		MvcResult result = mockMvc.perform(get("http://localhost:8080/zenstockapp/stocks"))
				.andExpect(status().isOk())
				.andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), true);
	}
	
}








