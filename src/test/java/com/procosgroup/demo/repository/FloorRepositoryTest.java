package com.procosgroup.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FloorRepositoryTest {
	
	@Autowired
	private FloorRepository floorRepository;
	
	@Test
	public void findAllFloors() {
		floorRepository.findAll();
	}

}
