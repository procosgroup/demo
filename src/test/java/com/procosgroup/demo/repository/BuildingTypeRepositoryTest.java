package com.procosgroup.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.procosgroup.demo.domain.BuildingType;

@SpringBootTest
@Transactional
@Sql(scripts = "setup.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
class BuildingTypeRepositoryTest {
	
	@Autowired
	BuildingTypeRepository buildingTypeRepository; 

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getBuildingTypes() {
		buildingTypeRepository.findAll();
	}
	
	@Test
	void getBuildingTypeSortByName() {
		List<BuildingType> types = buildingTypeRepository.findBySortByName("nl");	 
		assertFalse(types.isEmpty());
	}
	
	@Test
	void saveBuildingType() {
		BuildingType buildingType = new BuildingType("Office", "Office building");
		buildingType.setIdentifier("OFFICE");
		buildingTypeRepository.save(buildingType);
		
		buildingType.setName("nl", "Kantoor");
		buildingType.setDescription("nl", "Kantoorgebouw");
		
		buildingType.setName("fr", "Bureau");
		buildingType.setDescription("fr", "Bâtiment bureau");
		
		buildingTypeRepository.save(buildingType);
		
		Iterable<BuildingType> types = buildingTypeRepository.findAll();
		assertTrue(types.iterator().hasNext()); 		
	}

}
