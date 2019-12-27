package com.procosgroup.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query; 
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.procosgroup.demo.domain.Building;

@Repository
public interface BuildingRepository extends PagingAndSortingRepository<Building, Long>, QueryByExampleExecutor<Building>{
	
	@Query("from Building b where b.status = 'ACTIVE'")
	List<Building> findActiveBuildings();
	
	@Query("from Building b where b.status = 'ACTIVE'")
	Page<Building> findActiveBuildings(Pageable p);
	
	@Query("from Building b join b.attributes a where a.name = ?1 and a.stringValue = ?2")
	List<Building> findByAttribute(String name, String value);
	
	@Query("from Building b join b.attributes a where a.name = ?1 and a.dateValue = ?2")
	List<Building> findByAttribute(String name, Date value);
	
	Building findByIdentifier(String identifier);	 

}
