package com.procosgroup.demo.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.procosgroup.demo.domain.Building;
import com.procosgroup.demo.domain.Floor;

@Repository
public interface FloorRepository extends PagingAndSortingRepository<Floor, Long> {

	List<Floor> findByBuilding(Building building);
		
	Floor findByBuildingAndIdentifier(Building building, String identifier);
	
	

}
