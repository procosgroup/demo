package com.procosgroup.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.procosgroup.demo.domain.Building;
import com.procosgroup.demo.domain.Floor;
import com.procosgroup.demo.domain.Room;
import com.procosgroup.demo.domain.RoomCategory;
import com.procosgroup.demo.domain.RoomType;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
	
	List<Room> findByFloor(Floor floor);
	
	List<Room> findByFloorAndRoomType(Floor floor, RoomType roomType);
	
	List<Room> findByFloorAndRoomTypeRoomCategory(Floor floor, RoomCategory roomCategory);
	
	List<Room> findByFloorBuildingAndRoomType(Building building, RoomType roomType);

	@Query("from Room where floor = ?1 and roomType.roomCategory.occupiable = true")
	List<Room> findOccupiable(Floor floor);

}
