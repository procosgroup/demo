package com.procosgroup.demo.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.procosgroup.demo.domain.City;
import com.procosgroup.demo.domain.Country; 

public interface CityRepository extends PagingAndSortingRepository<City, Long>  {
	
	List<City> findByCountry(Country country);

}