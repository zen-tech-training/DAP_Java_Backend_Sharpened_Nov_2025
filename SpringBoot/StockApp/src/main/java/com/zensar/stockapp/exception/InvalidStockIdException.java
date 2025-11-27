package com.zensar.stockapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidStockIdException extends RuntimeException { //Unchecked exception

	private String message;

	public InvalidStockIdException() {
		this.message = "";
	}
	public InvalidStockIdException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String toString() {
		return "Stock is invalid " + this.message;
	}
	
}
