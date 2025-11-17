package com.zensar;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
@FunctionalInterface
interface MathOperation {
	int operate(int x, int y);
}

@FunctionalInterface
interface NameLength {
	int getLength(String str);
}
*/
class Contact2 {
	String name;
	public Contact2(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
public class LambdaMain {

	//Write a function that prints non-empty strings from list
	public static void printNonEmptyStrings(List<String> listOfStrings, Predicate<String> nonEmptyPredicate) {
		for(int i=0;i<listOfStrings.size();i++) {
			if(nonEmptyPredicate.test(listOfStrings.get(i)))
				System.out.println(listOfStrings.get(i));
		}
	}
	
	public static void printList(List<String> listOfStrings, Consumer<String> consumer) {
		for(int i=0;i<listOfStrings.size();i++) {
			//System.out.println(listOfStrings.get(i));
			consumer.accept(listOfStrings.get(i));
		}
	}
	
	//Write a program that returns a number
	public static void numberGenerator() {
		Random random = new Random();
		Supplier<Integer> noSupplier = () -> random.nextInt(100);
		System.out.println("Supplied " + noSupplier.get());
	}
	public static void main(String[] args) {
		
		Function<Contact2, String> func2 = (contact) -> contact.getName(); //Lambda
		Function<Contact2, String> func3 = Contact2::getName; //Method references
		Consumer<Contact2> consumer = (contact)->contact.setName("Ivan");
		
		Consumer<String> consumer3 = (str)->System.out.println(str);
		Consumer<String> consumer4 = System.out::println;
		
		String name = func2.apply(new Contact2("Tom"));
		System.out.println("Contact name: " + name);
		name = func3.apply(new Contact2("Jerry"));
		System.out.println("Contact name: " + name);
		
		//Write a lambda to check whether given number is even or not
		Predicate<Integer> predicate = (number) -> number%2==0; // number -> number.intValue() - unboxing
		IntPredicate intPredicate = (number) -> number%2==0; //primitive specilization
		
		System.out.println("Is 25 even " + predicate.test(25)); // 25 -> new Integer(25) - boxing
		System.out.println("Is 94 even " + predicate.test(94)); // 94 -> new Integer(94)
		System.out.println("Is 44 even " + intPredicate.test(44)); //No boxing required
		
		
		numberGenerator();
		
		List<String> list = Arrays.asList("ABC", "XYZ", "PQR");
		
		Predicate<String> nonEmptyPredicate = (str) -> !str.isEmpty();
		
		//printNonEmptyStrings(list, nonEmptyPredicate);
		
		Consumer<String> consoleConsumer = (str) -> System.out.println(str);
		printList(list, consoleConsumer);
		/*
		Consumer<String> fileConsumer = (str) -> file.write(str);
		printList(list, fileConsumer);
		*/
		
		//Write a lambda that returns the length of a string
		Function<String, Integer> lengthOpr = (String str) -> str.length();
		Function<String, Integer> lengthOpr2 = String::length;
		
		System.out.println("Length of Zensar is " + lengthOpr.apply("Zensar"));
		
		//Write a lambda that returns sum of two numbers
		BiFunction<Integer, Integer, Integer> addOpr = (x, y) -> x+y;
		BiFunction<Double, Double, Double> addDoubleOpr = (x, y) -> x+y;
		
		System.out.println("Sum = " + addOpr.apply(10, 20));
		System.out.println("Sum = " + addOpr.apply(90, 10));
		System.out.println("Double Sum = " + addDoubleOpr.apply(90.1, 10.2));
		BiFunction<Integer, Integer, Integer> substractOpr = (x, y) -> x-y;
		System.out.println("Subtract = " + substractOpr.apply(90, 10));
	}

}
