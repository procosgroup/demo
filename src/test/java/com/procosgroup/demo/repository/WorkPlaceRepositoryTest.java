package com.procosgroup.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorkPlaceRepositoryTest {

	@Autowired
	WorkPlaceRepository workPlaceRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSave() { 
	}

	@Test
	void testFindAll() { 
		workPlaceRepository.findAll();
	}

}
