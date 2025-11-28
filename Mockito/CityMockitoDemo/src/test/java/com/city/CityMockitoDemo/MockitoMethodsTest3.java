package com.city.CityMockitoDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MockitoMethodsTest3 {

	static CalcService calcService;
	
	@BeforeAll
	public static void initialize() {
		calcService = new CalcService();
	}
	
	@Test
	public void testReset() {
		calcService = mock(CalcService.class);
		
		when(calcService.add(5, 10)).thenReturn(15);
		//OR
		doReturn(15).when(calcService.add(5, 10));
		
		//when(calcService.divide(10, 0)).thenThrow(ArithmeticException.class);
		//doThrow(ArithmeticException.class).when(calcService.divide(10, 0));
		assertEquals(calcService.add(5, 10), 15);
		
		//reset(calcService);
		assertEquals(calcService.add(5, 10), 15);
	}
	
	@Test
	public void testRealMethod() {
		calcService = mock(CalcService.class);
		doCallRealMethod().when(calcService).add(5, 10);
		assertEquals(calcService.add(5, 10), 15);
	}
	
	@Test
	public void testSpy() {
		//calcService = mock(CalcService.class);
		calcService = spy(CalcService.class);
		assertEquals(calcService.add(5, 10), 15);
	}
	
	@Test
	public void testVerify() {
		calcService = mock(CalcService.class);
		calcService.add(5, 10);
		verify(calcService).add(5, 10);
	}
}

class CalcService {
	public int add(int x, int y) {
		return x + y;
	}
	public int divide(int x, int y) {
		return x / y;
	}
}


