package com.procosgroup.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.procosgroup.demo.domain.BuildingType;

@Repository
public interface BuildingTypeRepository extends CrudRepository<BuildingType, Long>{
	
	@Query("from BuildingType t join fetch t.localizations loc where KEY(loc) = ?1 order by VALUE(loc).name")
	List<BuildingType> findBySortByName(String locale);

}
