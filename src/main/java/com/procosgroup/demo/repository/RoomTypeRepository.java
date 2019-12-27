package com.procosgroup.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.procosgroup.demo.domain.RoomCategory;
import com.procosgroup.demo.domain.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
	 
	List<RoomType> findByRoomCategory(RoomCategory roomCategory);
	
	RoomType findByIdentifier(String identifier);
	
	@Query("from RoomType where roomCategory.usable = true")
	List<RoomType> findUsable();
	
	@Query("from RoomType where roomCategory.occupiable = true")
	List<RoomType> findOccupiable();
	
}
