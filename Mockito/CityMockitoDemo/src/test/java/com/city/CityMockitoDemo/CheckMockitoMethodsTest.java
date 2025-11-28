package com.city.CityMockitoDemo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CheckMockitoMethodsTest {

	//@Test
	public void testSpy() {
		List namesList = spy(List.class);
		namesList.add("ABC");
		namesList.add("XYZ");
		String name = (String)namesList.get(1);
		System.out.println("Name " + name);
	}

	@Test
	public void testMock() {
		List namesList = mock(ArrayList.class);
		//when(namesList.add(any())).thenCallRealMethod();
		
		doCallRealMethod().when(namesList).add("ABC");
		//when(namesList.get(anyInt()).thenCallRealMethod();
		namesList.add("ABC");
		//namesList.add("XYZ");
		//String name = (String)namesList.get(1);
		System.out.println("Name List " + namesList);
	}
}
