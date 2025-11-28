package com.city.CityMockitoDemo;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BusinessAppTest {

	
	//@Test
	public void testWithMockito() {
		
		CityService cityService = mock(CityService.class); //Getting stub automatically using Mockito
		when(cityService.findCitiesByCountries("USA")).thenReturn(Arrays.asList("New York", "Washington"));
		when(cityService.findCitiesByCountries("INDIA")).thenReturn(Arrays.asList("Delhi", "Mumbai", "Kolkatta", "Chennai"));
		
		BusinessApp businessApp = new BusinessApp(cityService);
		assertEquals(2, businessApp.retrieveCityListByCountry("USA").size());
		assertEquals(4, businessApp.retrieveCityListByCountry("INDIA").size());
	}
	
	@Test
	public void testWithMocking() {
		CityService cityService = mock(CityService.class);
		
		when(cityService.findCitiesByCountries("USA")).thenReturn(Arrays.asList("New York", "Washington"));
		when(cityService.findCitiesByCountries("INDIA")).thenReturn(Arrays.asList("Pune", "Mumbai", "Chennai", "Delhi"));
		
		BusinessApp businessApp = new BusinessApp(cityService);
		assertEquals(2, businessApp.retrieveCityListByCountry("USA").size());
		assertEquals(4, businessApp.retrieveCityListByCountry("INDIA").size());
	}
	
	
	//@Test
	public void testRetrieveCityListByCountry() {
		CityService cityService = new CityServiceImpl();
		
		BusinessApp businessApp = new BusinessApp(cityService);
		assertTrue(businessApp.retrieveCityListByCountry("INDIA").size()>0);
	}
	
	//@Test
	public void test() {
		CityService cityService = new CityServiceStub();
		BusinessApp businessApp = new BusinessApp(cityService);
		assertEquals(2, businessApp.retrieveCityListByCountry("USA").size());
		assertEquals(4, businessApp.retrieveCityListByCountry("INDIA").size());
	}

}
