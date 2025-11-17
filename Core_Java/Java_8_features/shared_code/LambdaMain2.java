package com.zensar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LambdaMain2 {

	public static void main(String[] args) {
		
		ArrayList<Transaction> transactionList = new ArrayList<>();
		transactionList.add(new Transaction(1, "Pune", 20000));
		transactionList.add(new Transaction(2, "Mumbai", 40000));
		transactionList.add(new Transaction(3, "Delhi", 10000));
		
		Comparator<Transaction> lambdaComparator = (transaction1, transaction2) -> 
				transaction1.getLocation().compareTo(transaction2.getLocation()); //Lambda
		Comparator<Transaction> methodReferenceComparator = 
				Comparator.comparing(Transaction::getAmount).reversed(); //method references
		
		//Collections.sort(transactionList, new TransactionLocationComparator());
		//Collections.sort(transactionList, lambdaComparator);
		Collections.sort(transactionList, methodReferenceComparator);
		
		System.out.println("Sorted transaction list = " + transactionList);
	}

}
class TransactionLocationComparator implements Comparator<Transaction> {
	@Override
	public int compare(Transaction transaction1, Transaction transaction2) {
		return transaction1.getLocation().compareTo(transaction2.getLocation());
	}
}