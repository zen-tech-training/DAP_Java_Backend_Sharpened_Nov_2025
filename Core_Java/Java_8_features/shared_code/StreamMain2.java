package com.zensar;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import static java.util.stream.Collectors.*;

public class StreamMain2 {

	public static void main(String[] args) {
		List<Transaction> transactionList = new ArrayList<>();
		transactionList.add(new Transaction(1, "Pune", 20000));
		transactionList.add(new Transaction(2, "Mumbai", 40000));
		transactionList.add(new Transaction(3, "Delhi", 10000));
		transactionList.add(new Transaction(2, "Mumbai", 40000));

		//Natural sorting using Comparable
		//List<Transaction> shortlistedTransactionsList = transactionList.stream().sorted().collect(Collectors.toList());
		//System.out.println("Transaction amount list: " + shortlistedTransactionsList);
		
		//Custom sorting using Comparator
		//Comparator<Transaction> comparator = (t1, t2)->(int)(t2.getAmount()-t1.getAmount());
		//Comparator<Transaction> comparator = Comparator.comparing(Transaction::getLocation);
		//shortlistedTransactionsList = transactionList.stream().sorted(comparator).collect(Collectors.toList());
		//System.out.println("Custom Transaction amount list: " + shortlistedTransactionsList);
		
		
		
		
		
		/*
		//findAny()
		Optional<Transaction> opFilteredTransaction = transactionList.stream()
				.filter((transaction)->transaction.getLocation().equalsIgnoreCase("Mumbai"))
				.findAny();
		//if(filteredTransaction!=null) { //Old	
		//}
		
		
		if(opFilteredTransaction.isPresent()) { //New
			Transaction transaction = opFilteredTransaction.get();
		}
		*/
		
		//allMatch(), anyMatch(), noneMatch()
		/*
		boolean result = transactionList.stream()
				.noneMatch((transaction)->transaction.getAmount()>50000);
		System.out.println("Result: " + result);
		*/
		
		//map()
		//Function<Transaction, Double> mapperFunc = (transaction)->transaction.getAmount();
		
		Function<Transaction, Double> mapperFunc = Transaction::getAmount;
		/*
		List<Double> shortlistedTransactionsList = transactionList.stream()
				.map(Transaction::getAmount)
				.collect(Collectors.toList());
		System.out.println("Transaction amount list: " + shortlistedTransactionsList);
		*/
		
		double totalAmount = transactionList.stream()
			.map(Transaction::getAmount).reduce(0.0, Double::sum);
		System.out.println("totalAmount = " + totalAmount);
		
		double maxAmount = transactionList.stream()
				.map(Transaction::getAmount).reduce(Double::max).get();
			System.out.println("maxAmount = " + maxAmount);
		double minAmount = transactionList.stream()
				.map(Transaction::getAmount).reduce(Double::min).get();
			System.out.println("minAmount = " + minAmount);
		
		//Print the list
		//Consumer<Transaction> consumer = (transaction)->System.out.println(transaction);
		Consumer<Transaction> consumer = System.out::println;
		transactionList.stream().forEach(consumer);
		
		//limit() & skip()
		/*
		List<Transaction> shortlistedTransactionsList = transactionList.stream()
				.skip(1)
				.limit(2)
			.collect(Collectors.toList());
		System.out.println("shortlistedTransactionsList = " + shortlistedTransactionsList);
		*/
		
		//distinct
		
		List<Transaction> shortlistedTransactionsList = transactionList.stream()
				.distinct()
				.filter((transaction)->transaction.getLocation().equalsIgnoreCase("Mumbai"))
			.collect(toList());
		System.out.println("shortlistedTransactionsList = " + shortlistedTransactionsList);
		
	}

}
