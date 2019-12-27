package com.procosgroup.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.procosgroup.demo.domain.Country;

@SpringBootTest
class CountryRepositoryTest {
	
	@Autowired
	CountryRepository countryRepository;

	@BeforeEach
	void setUp() throws Exception {
		Country country = new Country("BE"); 
		country.setName("en", "Belgium");
		country.setName("nl", "België");
		country.setName("fr", "Belgique");

		countryRepository.save(country);	

		country = new Country("FR"); 
		country.setName("en", "France");
		country.setName("nl", "Frankrijk");
		country.setName("fr", "France");

		countryRepository.save(country);		
		
		country = new Country("AU"); 
		country.setName("en", "Australia");
		country.setName("nl", "Australië");
		country.setName("fr", "Australie");

		countryRepository.save(country);		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getAllCountries() {
		Iterable<Country> countries = countryRepository.findAll();
		assertTrue(countries.iterator().hasNext());		
	}

	@Test
	void getCountriesOrderByCode() {
		List<Country> countries = countryRepository.findByOrderByCountryCode();
		assertFalse(countries.isEmpty());		
		assertEquals("Australië", countries.get(0).getName("nl"));   
	}
	
	@Test
	void getCountriesOrderByName() {
		List<Country> countries = countryRepository.findByOrderByCountryName("nl");
		assertFalse(countries.isEmpty());		
		assertEquals("Australië", countries.get(0).getName("nl"));  
		assertEquals("France", countries.get(2).getName("fr"));  
	}
	
}
