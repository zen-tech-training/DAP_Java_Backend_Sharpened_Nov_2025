package com.city.CityMockitoDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BusinessAppMockingTest {

	@Test
	public void testBusinessApp() {
		CityService cityService = mock(CityService.class);
		BusinessApp businessApp = new BusinessApp(cityService);
		
		when(cityService.findCitiesByCountries("India")).thenReturn(Arrays.asList("Mumbai", "Pune", "Delhi", "Hyderabad"));
		when(cityService.findCitiesByCountries("USA")).thenReturn(Arrays.asList("NewYork", "Washington"));
		assertEquals(businessApp.retrieveCityListByCountry("USA").size(), 2);
	}
	
}
