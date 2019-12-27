package com.procosgroup.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example; 
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.procosgroup.demo.domain.Address;
import com.procosgroup.demo.domain.Area;
import com.procosgroup.demo.domain.Attribute;
import com.procosgroup.demo.domain.Building;
import com.procosgroup.demo.domain.City;
import com.procosgroup.demo.domain.ContactInfo;
import com.procosgroup.demo.domain.Country;
import com.procosgroup.demo.enumeration.AreaType;

@SpringBootTest
@Transactional
@Sql(scripts = "setup.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class BuildingRepositoryTest {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@BeforeEach
	void setUp() throws Exception {  
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	public void findBuildings() {
		List<Building> buildings = buildingRepository.findActiveBuildings();	
		assertFalse(buildings.isEmpty());
	}
	
	@Test
	public void findBuildingsByExample() {		
		Example<Building> example = Example.of(new Building("HQ"));		 
		// Buildings with identifier HQ and status ACTIVE and OWNED !
		Iterable<Building> buildings = buildingRepository.findAll(example);
		assertTrue(buildings.iterator().hasNext());
	}
	
	@Test
	public void addBuilding() {		
		Country country = countryRepository.save(new Country("BE"));		
		City city = cityRepository.save(new City(country, "Belgium"));
		
		Building building = new Building();
		building.setName("PROCOS HQ");
		building.setIdentifier("JB1");	
		building.setAddress(new Address(country, city, "2018", "Jan Blockxstraat", "1"));	
		
		building.setContactInfo(new ContactInfo("Bart", "00 32 3 256 87 79", null, "bart.vanderschoot@gmail.com", null));
		
		building.addAttributes(new Attribute("comments", "comments of the building")); 
		building.addAttributes(new Attribute("date", new Date())); 
		
		building.addArea(new Area(AreaType.EXTERN, 5218.36));
		
		Building savedBuilding = buildingRepository.save(building); 
		
		Area area = savedBuilding.getArea(AreaType.EXTERN).get();
		
		assertEquals(5218.36, area.getArea());
		
		Assertions.assertTrue(savedBuilding.hasAttribute("comments"));			
		
		List<Building> buildings = buildingRepository.findByAttribute("comments", "comments of the building");
		 		
		buildings = buildingRepository.findByAttribute("date", new Date());
		 
		assertFalse(buildings.isEmpty());
		
		buildingRepository.delete(building);
		
	}

}
