package com.procosgroup.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.procosgroup.demo.domain.RoomType;

@SpringBootTest
@Sql(scripts = "setup.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
class RoomTypeRepositoryTest {
	
	@Autowired
	RoomTypeRepository roomTypeRepository;

	@BeforeEach
	void setUp() throws Exception { 
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getAllRoomTypes() { 
		List<RoomType> roomTypes = roomTypeRepository.findAll();		
		assertFalse(roomTypes.isEmpty());
	}
	
	@Test
	void getUsableRoomTypes() { 
		List<RoomType> roomTypes = roomTypeRepository.findUsable();		
		assertFalse(roomTypes.isEmpty());
	}
	
	@Test
	void getOccupiableRoomTypes() { 
		List<RoomType> roomTypes = roomTypeRepository.findOccupiable();		
		assertFalse(roomTypes.isEmpty());
	}


}
