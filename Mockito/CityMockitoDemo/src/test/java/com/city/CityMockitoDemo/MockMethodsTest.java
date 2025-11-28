package com.city.CityMockitoDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class MockMethodsTest {

	@Test
	public void testMockRealMethod() {
		StringOperationsApp mockTestApp = mock(StringOperationsApp.class);
		doCallRealMethod().when(mockTestApp).getLength(anyString());
		int len = mockTestApp.getLength("Zensar");
		System.out.println("Mock Length: " + len);
		assertEquals(len, 6);
		
	}
	
	
	@Test
	public void testMock() {
		ArrayList list = mock(ArrayList.class);
		list.add("ABC");
		list.add("PQR");
		list.add("XYZ");
		System.out.println("Mock Size of list = " + list.size());
	}
	@Test
	public void testSpy() {
		ArrayList list = spy(ArrayList.class);
		list.add("ABC");
		list.add("PQR");
		list.add("XYZ");
		System.out.println("Spy Size of list = " + list.size());
		
	}
	
	@Test
	public void testVerify() {
		StringOperationsApp opr = mock(StringOperationsApp.class);
		when(opr.getLength("Zensar")).thenReturn(6);
		when(opr.getLength("IBM")).thenReturn(3);
		when(opr.getLength("Oracle")).thenThrow(RuntimeException.class);
		//reset(opr); //Removes all when rules
		
		assertEquals(opr.getLength("Oracle"), 6);
		
		verify(opr).getLength("Zensar");
	}
	
}

class StringOperationsApp {
	public int getLength(String str) {
		return str.length();
	}
}