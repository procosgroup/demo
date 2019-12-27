package com.procosgroup.demo.rest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.procosgroup.demo.domain.BuildingType;
import com.procosgroup.demo.domain.Country; 
import com.procosgroup.demo.repository.CountryRepository;
import com.procosgroup.demo.util.QueryUtils;

import lombok.Getter;
import lombok.Setter;

@CrossOrigin
@RestController
@Transactional
public class CountryRestController {

	@Autowired
	@Setter @Getter
	private CountryRepository countryRepository;
	
	@RequestMapping("/api/countries/all")
	@ResponseBody
	public Page<Country> getCountries(
			@RequestParam(value = "start", required = true) Integer start,
			@RequestParam(value = "end", required = true) Integer end
	) {		
		// PageRequest.of(page, size)
		return countryRepository.findAll(QueryUtils.getPageableFrom(start, end, "countryCode", "ASC"));	 
	} 
	
	@RequestMapping("/api/countries")
	@ResponseBody
	public List<Country> getCountries(@RequestParam(value = "locale", required = false)  final String _locale) {		
		final String locale = (_locale != null) ? _locale : Locale.getDefault().getLanguage();
		
		List<Country> countries = countryRepository.findByOrderByCountryCode();	
		 
		Collections.sort(countries, new Comparator<Country>() {
			@Override
			public int compare(Country o1, Country o2) { 
				return o1.getName(locale).compareTo(o2.getName(locale));
			}			
		});
		
		return countries;
	} 	
	
	@PostMapping("/api/countries")
	@ResponseBody
	public Country saveCountry(Country country) {		
		return countryRepository.save(country);		
	} 
	
}
