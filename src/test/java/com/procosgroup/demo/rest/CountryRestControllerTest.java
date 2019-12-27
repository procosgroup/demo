package com.procosgroup.demo.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.procosgroup.demo.domain.Country;
import com.procosgroup.demo.repository.CountryRepository;

@SpringBootTest
@AutoConfigureMockMvc
class CountryRestControllerTest {
	
	@Autowired
    private MockMvc mvc;
		
	@Autowired
	private CountryRepository countryRepository; 

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

	@WithMockUser(value = "spring")
	@Test
	void testGetCountries() throws Exception { 
		mvc.perform(get("/api/countries")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk()) 
			      .andExpect(jsonPath("$[0].countryCode", is("AU")))
				  .andExpect(jsonPath("$[0].names.strings.en", is("Australia")));
	}

	@Test
	void testSaveCountry() {
		// fail("Not yet implemented");
	}

}
