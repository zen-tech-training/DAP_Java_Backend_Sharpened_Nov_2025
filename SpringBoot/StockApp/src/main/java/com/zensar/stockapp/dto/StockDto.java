package com.zensar.stockapp.dto;

import io.swagger.v3.oas.annotations.Parameter;

public class StockDto {

	@Parameter(required = false)
	private int id;
	private String name;
	private String marketName;
	private double price;
	public StockDto() {}
	public StockDto(int id, String name, String market, double price) {
		super();
		this.id = id;
		this.name = name;
		this.marketName = market;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String market) {
		this.marketName = market;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "StockDto [id=" + id + ", name=" + name + ", market=" + marketName + ", price=" + price + "]";
	}
	
}
