package com.java;

sealed class Employee permits Manager, Labour {
	int id;
	String name;
}
non-sealed class Manager extends Employee { //final, sealed, non-sealed
	
}
final class Labour extends Employee { //final, sealed, non-sealed
	
}
class Circle { //No compilation error
	
}
final class SalesManager extends Manager {
	
}
public class SealedClassesMain {

	public static void main(String[] args) {

	}

}
