package com.procosgroup.demo.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.procosgroup.demo.domain.Country; 

public interface CountryRepository extends PagingAndSortingRepository<Country, String>  {
	
	List<Country> findByOrderByCountryCode(); 
	
	@Query("select c, VALUE(n) as name from Country c join c.names.strings n where KEY(n) = ?1 order by name")
	List<Country> findByOrderByCountryName(String locale); 
}
