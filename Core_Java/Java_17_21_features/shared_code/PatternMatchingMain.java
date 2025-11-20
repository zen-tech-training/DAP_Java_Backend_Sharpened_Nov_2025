package com.java;

record Point(int x, int y) {}
record Circle(int radius) {}
record Rectangle(int length, int width) {}
record Triangle(int base, int height) {}
record Line(Point start, Point end) {}

public class PatternMatchingMain {
	
	private static void switchWithReturn() {
		String day = "Thursday";
		String message = switch(day) { //switch varible can be number, string or enum
			case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "Working day";
			case "Saturday" -> "Half day Saturday";
			case "Sunday" -> "Holiday and Fun day";
			default -> { yield "Invalid day"; } //yield is equivalent to the return statement
		};
		System.out.println("returned message = " + message);

	}
	
	private static void nestedRecordPatternMatching() {
		Line line = new Line(new Point(1,3), new Point(4,5));
		if(line instanceof Line(Point(int x1, int y1), Point(int x2, int y2))) {
			System.out.println(x1 +" " + y1 + "\t" + x2 + " " + y2);
		}
	}
	
	private static void recordPatternsWithConditions(Object obj) {
		switch(obj) {
			case Circle(int radius) when radius>20 -> System.out.println("Circle radius>20: " + radius);
			case Circle(int radius) when radius<=20 -> System.out.println("Circle radius<=20: " + radius);
			default -> System.out.println("Invalid object type");
		}
	}
	
	private static void recordPatternsUsingSwitch(Object obj) {
		switch(obj) {
			case Circle(int radius) -> System.out.println("Circle radius: " + radius);
			case Rectangle(int length, int width) -> System.out.println("Rectangle " + length +" - " + width);
			default -> System.out.println("Invalid object type");
		}
	}
	
	private static void basicRecordPatternMatching() {
		Point point = new Point(5, 4);
		if(point instanceof Point(int x, int y)) {
			System.out.println("x = " + x + "\ty = " + y);
		}
		String str = "Hello";
		if(str instanceof String) {
			
		}
	}
	public static void main(String[] args) {
		//basicRecordPatternMatching();
		//recordPatternsUsingSwitch(new Point(4, 6));
		//recordPatternsWithConditions(new Circle(22));
		//nestedRecordPatternMatching();
		switchWithReturn();
	}

}
