package com.procosgroup.demo.rest;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.procosgroup.demo.domain.Building;
import com.procosgroup.demo.domain.BuildingType;
import com.procosgroup.demo.repository.BuildingRepository;
import com.procosgroup.demo.repository.BuildingTypeRepository;

import lombok.Getter;
import lombok.Setter;

@CrossOrigin
@RestController
@Transactional
public class BuildingRestController {
	
	@Autowired
	@Setter @Getter
	private BuildingRepository buildingRepository;
	
	@Autowired
	@Setter @Getter
	private BuildingTypeRepository buildingTypeRepository;
	
	
	@RequestMapping("/api/building/types")
	@ResponseBody
	public Iterable<BuildingType> getBuildingTypes() {		
		Iterable<BuildingType> types = buildingTypeRepository.findAll();	
		
		System.err.println(types);
		
		return types;
	} 
	
	@RequestMapping("/api/building/types/{id}")
	@ResponseBody
	public BuildingType getBuildingType(@PathVariable("id") Long id) {		
		Optional<BuildingType> result = buildingTypeRepository.findById(id);		
		return result.isPresent() ? result.get() : null;
	} 
	
	@PostMapping("/api/buildingTypes")
	@ResponseBody
	public BuildingType saveBuildingTypes(BuildingType buildingType) {		
		return buildingTypeRepository.save(buildingType);		
	} 
	
	@RequestMapping("/api/buildings/active")
	public List<Building> getActiveBuildings() {		
		return buildingRepository.findActiveBuildings();		
	} 
	
	@RequestMapping("/api/buildings/all")
	@ResponseBody
	public Iterable<Building> getAllBuildings() {		
		return buildingRepository.findAll();		
	} 
	
	@RequestMapping("/api/buildings")
	public Page<Building> getBuildings(
			@RequestParam(value = "_start", required = false) Integer start,
			@RequestParam(value = "_end", required = false) Integer end,
			@RequestParam(value = "_sort", required = false) String sort,
			@RequestParam(value = "_order", required = false) String order) {	
		Pageable pageable = getPageableFrom(start, end, sort, order);
		return buildingRepository.findActiveBuildings(pageable);		
	} 
	
	protected Pageable getPageableFrom(Integer start, Integer end, String sort, String order) {
		if (start != null && end != null) {
			int size = end - start;
			return PageRequest.of(start / size, size, Sort.by(Direction.fromString(order), sort));
		} else {
			return Pageable.unpaged();
		}
	}
	
	@RequestMapping("/api/buildings/{id}")
	public Building getBuilding(@PathVariable("id") Long id) {		
		Optional<Building> building = buildingRepository.findById(id);
		return building.isPresent() ? building.get() : null;		
	} 
	
	@PostMapping(value="/api/buildings", consumes="application/json", produces="application/json") 
	@ResponseBody
	public Building saveBuilding(@RequestBody Building building, Principal principal) {
				
		return buildingRepository.save(building);
	} 

}
