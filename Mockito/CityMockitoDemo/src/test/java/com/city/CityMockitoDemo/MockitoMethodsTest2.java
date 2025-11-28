package com.city.CityMockitoDemo;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class MockitoMethodsTest2 {

	@Test
	public void testMock() {
		Operation operation = mock(Operation.class);
		when(operation.getLength(anyString())).thenCallRealMethod();
		when(operation.getLength(null)).thenThrow(NullPointerException.class);
		//reset(operation);
		System.out.println("Mocking Length of the string = " + operation.getLength("Zensar"));
		System.out.println("Mocking Length of the string = " + operation.getLength(null));
	}
	@Test
	public void testSpy() {
		Operation operation = spy(Operation.class);
		System.out.println("Spy Length of the string = " + operation.getLength("Zensar"));
	}
}
class Operation {
	public int getLength(String str) {
		return str.length();
	}
}
