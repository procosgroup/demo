package com.procosgroup.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.procosgroup.demo.domain.City;
import com.procosgroup.demo.domain.Country;

@SpringBootTest
class CityRepositoryTest {
	
	@Autowired
	CityRepository cityRepository; 

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindByCountry() { 		
		cityRepository.findByCountry(new Country("BE"));
	}

	@Test
	void testSave() {
		City city = new City();
		
		cityRepository.save(city);
	}

}
