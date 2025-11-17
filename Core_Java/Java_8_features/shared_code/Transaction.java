package com.zensar;

import java.util.Objects;

public class Transaction implements Comparable<Transaction> {
	int id;
	String location;
	double amount;
	public Transaction() {}
	public Transaction(int id, String location, double amount) {
		super();
		this.id = id;
		this.location = location;
		this.amount = amount;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object object) {
		Transaction transaction = (Transaction)object;
		if(this.id==transaction.id)
			return true;
		return false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", location=" + location + ", amount=" + amount + "]";
	}
	@Override
	public int compareTo(Transaction transaction) {
		if(this.amount > transaction.amount)
			return 1;
		else if(this.amount < transaction.amount)
			return -1;
		return 0;
	}
}



