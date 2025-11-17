package com.zensar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StrreamMain {

	public static void main(String[] args) {
		List<Transaction> transactionList = new ArrayList<>();
		transactionList.add(new Transaction(1, "Pune", 20000));
		transactionList.add(new Transaction(2, "Mumbai", 40000));
		transactionList.add(new Transaction(3, "Delhi", 10000));
		
		for(Transaction t: transactionList) {
			System.out.println("Collection print 1: " + t);
		}
		for(Transaction t: transactionList) {
			System.out.println("Collection print 2: " + t);
		}
		
		//Write a program to print transactions where amount > 15000 & sorted by amount in descending fashion
		
		Stream<Transaction> stream = transactionList.stream(); //Getting stream environment
		
		Predicate<Transaction> predicate = (transaction) -> transaction.getAmount() > 15000;
		stream = stream.filter(predicate);
		stream = stream.sorted(Comparator.comparing(Transaction::getAmount).reversed());
		
		List<Transaction> shortlistedTransactions = stream.collect(Collectors.toList()); //convert stream into output
		System.out.println("shortlistedTransactions = " + shortlistedTransactions);
		
		stream = transactionList.stream(); //Getting stream environment
		stream.forEach((trsnaction)->System.out.println(trsnaction));
		stream.forEach((trsnaction)->System.out.println(trsnaction));
	}

}




