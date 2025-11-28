package com.city.CityMockitoDemo;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MockingTest {

	@Test
	public void testMocking() {
		List<String> list = mock(ArrayList.class);
		list.add("ABC");
		list.add("XYZ");
		System.out.println("List Size = " + list.size());
	}
	
}
