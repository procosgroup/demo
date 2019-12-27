package com.procosgroup.demo.rest;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.client.RestTemplate;

import com.procosgroup.demo.domain.Building;
import com.procosgroup.demo.domain.BuildingType;
import com.procosgroup.demo.repository.BuildingRepository;
import com.procosgroup.demo.repository.BuildingTypeRepository;

@SpringBootTest
//@RestClientTest(BuildingRestController.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@EnableAutoConfiguration(exclude = {
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
})
// @Profile("test")
@Transactional
class BuildingRestControllerTest {
	
	@Autowired
	BuildingRestController buildingRestController;

	@Autowired
	BuildingRepository buildingRepository;
	
	@Autowired
	BuildingTypeRepository buildingTypeRepository;
	
	// @Autowired
	// MockRestServiceServer server;
	
	@Autowired
	RestTemplate restTemplate;
	
	List<Building> buildings = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		BuildingType buildingType = new BuildingType("Office", "Office building");
		buildingTypeRepository.save(buildingType);
		
		Building building = new Building();
		building.setName("test");
		building.setIdentifier("HQ");	
		building.setBuildingType(buildingType);
		
		buildings.add(building);
		
		// buildingRepository.save(building);		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	 
	
	@Profile("test")
	@Test
	void testGetAllBuildings() {
		 String url = "http://localhost:8080/api/buildings";
		 
		 ResponseEntity<Building[]> response = restTemplate.getForEntity(url, Building[].class);
		 
		 // this.server.expect(requestTo(url)).andRespond(withSuccess());
		 
		 

	    // ResponseEntity<String> response = buildingRestController.getAllBuildings();
		//Iterable<Building> buildings = buildingRestController.getAllBuildings();
		//System.err.println(buildings);
	}

	@Test
	void testGetActiveBuildings() {
		List<Building> buildings = buildingRestController.getActiveBuildings();
		System.err.println(buildings);
	}

	@Test
	void testGetBuildings() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPageableFrom() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBuilding() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveBuilding() {
		fail("Not yet implemented");
	}

	@Test
	void testSetBuildingRepository() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBuildingRepository() {
		fail("Not yet implemented");
	}

}
