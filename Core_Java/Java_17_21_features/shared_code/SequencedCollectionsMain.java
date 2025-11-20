package com.java;

import java.util.ArrayList;
import java.util.List;

public class SequencedCollectionsMain {

	public static void main(String[] args) {

		List<String> list = new ArrayList<>();
		list.add("ABC");
		list.add("PQR");
		list.add("XYZ");
		//old way
		//System.out.println("First: " + list.get(0));
		
		System.out.println("First: " + list.getFirst());
		System.out.println("Last: " + list.getLast());
	}

}
