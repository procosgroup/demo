package com.procosgroup.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.procosgroup.demo.domain.RoomCategory;

@SpringBootTest
@Transactional
class RoomCategoryRepositoryTest {
	
	@Autowired
	RoomCategoryRepository roomCategoryRepository;

	@BeforeEach
	void setUp() throws Exception {
		RoomCategory roomCategory = new RoomCategory("dummy", "this is a test", false, false);
		roomCategoryRepository.save(roomCategory);
		
		roomCategory = new RoomCategory("occupiable", "this is a test", true, true);
		roomCategoryRepository.save(roomCategory);
		
		roomCategory = new RoomCategory("usable", "this is a test", false, true);
		roomCategoryRepository.save(roomCategory);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void findAll() {
		List<RoomCategory> cats = roomCategoryRepository.findAll();		
		assertFalse(cats.isEmpty());
		assertEquals(3, cats.size());
	}
	
	@Test
	void findUsable() {
		List<RoomCategory> cats = roomCategoryRepository.findUsable();		
		assertFalse(cats.isEmpty()); 
		assertEquals(2, cats.size());
	}
	
	@Test
	void findOccupiable() {
		List<RoomCategory> cats = roomCategoryRepository.findOccupiable();		
		assertFalse(cats.isEmpty()); 
		assertEquals(1, cats.size());
	}
	
	
	@Test
	void update() {
		RoomCategory roomCategory = new RoomCategory("shaft", "this is a test", false, false);
		roomCategoryRepository.save(roomCategory);
		
		roomCategory.setIdentifier("SHAFT");
		roomCategoryRepository.save(roomCategory);
		
		roomCategory.setName("nl", "schacht");
		
		roomCategoryRepository.save(roomCategory);		
		
		RoomCategory saved = roomCategoryRepository.getOne(roomCategory.getId());
		
		assertEquals("schacht", saved.getName("nl"));
		
		roomCategoryRepository.delete(saved);
	}

}
