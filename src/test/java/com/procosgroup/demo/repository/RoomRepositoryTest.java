package com.procosgroup.demo.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.procosgroup.demo.domain.Building;
import com.procosgroup.demo.domain.Floor;
import com.procosgroup.demo.domain.Room;

@SpringBootTest
@Transactional
@Sql(scripts = "setup.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class RoomRepositoryTest {
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private FloorRepository floorRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Test
	public void findAllRooms() {
		Iterable<Room> rooms = roomRepository.findAll();	
		assertTrue(rooms.iterator().hasNext());
	}
	
	@Test
	public void findOccupiableRooms() {
		Building building = buildingRepository.findByIdentifier("HQ");
		Floor floor = floorRepository.findByBuildingAndIdentifier(building, "00");
		List<Room> rooms = roomRepository.findOccupiable(floor);
		assertFalse(rooms.isEmpty());
	}
	
	

}
