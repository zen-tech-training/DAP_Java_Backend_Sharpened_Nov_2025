package com.city.CityMockitoDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class MockitoMethodsTest {

	//@Test
	public void testSpy() {
		TestApp spyTestApp = spy(TestApp.class);
		int len = spyTestApp.getLength("Zensar");
		System.out.println("Spy Length: " + len);
		assertEquals(len, 6);
	}
	
	//@Test
	public void testReal() {
		TestApp testApp = new TestApp();
		assertEquals(testApp.getLength("Zensar"), 6);
		verify(testApp).getLength("Zensar");
	}

	@Test
	public void testMocking() {
		TestApp mockTestApp = mock(TestApp.class);
		doCallRealMethod().when(mockTestApp).getLength(anyString());
		int len = mockTestApp.getLength("Zensar");
		System.out.println("Mock Length: " + len);
		assertEquals(len, 6);
		//verify(mockTestApp).getLength("Zensar");
	}

}

class TestApp {
	public int getLength(String str) {
		return str.length();
	}
}