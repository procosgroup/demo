package com.procosgroup.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.procosgroup.demo.domain.RoomCategory; 

@Repository
public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {
	
	@Query("from RoomCategory where occupiable = true")
	List<RoomCategory> findOccupiable();
	 
	@Query("from RoomCategory where usable = true")
	List<RoomCategory> findUsable();
	
	RoomCategory findByIdentifier(String identifier);
	 
}
