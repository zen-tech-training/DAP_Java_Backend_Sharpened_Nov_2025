package com.city.CityMockitoDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BusinessAppMockitoDemoTest {

	
	@Test
	public void testRetrieveCityListByCountry() {
		CityService cityService = mock(CityService.class);
		when(cityService.findCitiesByCountries("INDIA")).thenReturn(Arrays.asList("Mumbai", "Pune", "Bangalore"));
		when(cityService.findCitiesByCountries("USA")).thenReturn(Arrays.asList("NewYork", "Washington"));
		BusinessApp businessApp = new BusinessApp(cityService);
		assertEquals(businessApp.retrieveCityListByCountry("INDIA").size(), 3);
	}
}
