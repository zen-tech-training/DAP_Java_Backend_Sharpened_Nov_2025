package com.city.CityMockitoDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BusinessAppMockitoTest_2 {

	@Test
	public void testRetrieveCityListByCountry() {
		CityService cityService = mock(CityService.class);
		
		when(cityService.findCitiesByCountries("INDIA"))
			.thenReturn(Arrays.asList("Delhi", "Mumbai", "Kolkatta", "Chennai"));
		when(cityService.findCitiesByCountries("USA"))
		.thenReturn(Arrays.asList("New York", "Washington"));
		
		
		BusinessApp businessApp = new BusinessApp(cityService);
		assertEquals(businessApp.retrieveCityListByCountry("INDIA").size(), 4);
		assertEquals(businessApp.retrieveCityListByCountry("USA").size(), 2);
	}
}






