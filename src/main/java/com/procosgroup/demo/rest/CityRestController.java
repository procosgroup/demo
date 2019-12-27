package com.procosgroup.demo.rest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.procosgroup.demo.domain.City;
import com.procosgroup.demo.domain.Country;
import com.procosgroup.demo.repository.CityRepository;
import com.procosgroup.demo.util.QueryUtils;

import lombok.Getter;
import lombok.Setter;

@CrossOrigin
@RestController
@Transactional
public class CityRestController {
	
	@Autowired
	@Setter @Getter
	private CityRepository cityRepository;
	
	@RequestMapping("/api/cities/all")
	@ResponseBody
	public Page<City> getCities(
			@RequestParam(value = "start", required = true) Integer start,
			@RequestParam(value = "end", required = true) Integer end
	) {		
		// PageRequest.of(page, size)
		return cityRepository.findAll(QueryUtils.getPageableFrom(start, end, "identifier", "ASC"));	 
	} 
	
	@RequestMapping("/api/cities")
	@ResponseBody
	public List<City> getCities(
		@RequestParam(value = "locale", required = true)  final String locale,
		@RequestParam(value = "country", required = true)  final String countryCode
	
	) {		
		
		List<City> cities = cityRepository.findByCountry(new Country(countryCode));	
		 
		Collections.sort(cities, new Comparator<City>() {
			@Override
			public int compare(City o1, City o2) { 
				return o1.getName(locale).compareTo(o2.getName(locale));
			}			
		});
		
		return cities;
	} 	
	
	@PostMapping("/api/cities")
	@ResponseBody
	public City saveCity(City City) {		
		return cityRepository.save(City);		
	} 
	

}
