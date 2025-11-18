package com.zensar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamMain3 {

	public static void main(String[] args) {
		List<Transaction> transactionList = new ArrayList<>();
		transactionList.add(new Transaction(1, "Pune", 20000));
		transactionList.add(new Transaction(2, "Mumbai", 40000));
		transactionList.add(new Transaction(3, "Delhi", 10000));
		transactionList.add(new Transaction(4, "Mumbai", 30000));
		transactionList.add(new Transaction(5, "Pune", 2000));
		
		//Find out total transaction amount by its location
		Map<String, List<Transaction>> mapTransactionLocation = 
				transactionList.stream().collect(Collectors.groupingBy(Transaction::getLocation)); //single level grouping
		System.out.println("mapTransactionLocation = " + mapTransactionLocation);
		
		Map<String, Long> mapTransactionLocationCount = 
				transactionList.stream().collect(Collectors.groupingBy(Transaction::getLocation, Collectors.counting())); //subgrouping
		System.out.println("mapTransactionLocationCount = " + mapTransactionLocationCount);
		
		//Find out high value transactions (amount>15000)
		Map<Boolean, List<Transaction>> mapTransactionHighLowValue = 
				transactionList.stream().collect(Collectors.partitioningBy((transaction)->transaction.getAmount()>15000));
		System.out.println("mapTransactionHighLowValue = " + mapTransactionHighLowValue);
		List<Transaction> highValueTransactions = mapTransactionHighLowValue.get(true);
		System.out.println("High Value Transactions= " + highValueTransactions);
		
		Comparator priceComparator = Comparator.comparingDouble(Transaction::getAmount);
		Optional<Transaction> opMaxPriceTransaction = transactionList.stream().collect(Collectors.maxBy(priceComparator));
		System.out.println("\nMax price transaction: " + opMaxPriceTransaction.get());
		Optional<Transaction> opMinPriceTransaction = transactionList.stream().collect(Collectors.minBy(priceComparator));
		System.out.println("\nMin price transaction: " + opMinPriceTransaction.get());
		
		String allLocations = transactionList.stream().map(Transaction::getLocation).collect(Collectors.joining(","));
		System.out.println("allLocations = " + allLocations);
	}

}
