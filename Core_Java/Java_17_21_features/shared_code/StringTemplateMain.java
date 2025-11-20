package com.java;

import static java.lang.StringTemplate.STR;

public class StringTemplateMain {

	public static void main(String[] args) {
		//Text Block
		String message = """
				I
				like
				Java 21
				""";
		System.out.println(message);
		
		String name = "Tom";
		int age = 21;
		message = STR."My name is \{name} and my age is \{age}";
		System.out.println(message);
	}

}
