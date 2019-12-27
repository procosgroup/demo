package com.procosgroup.demo.rest;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.procosgroup.demo.domain.Building;
import com.procosgroup.demo.domain.Floor;
import com.procosgroup.demo.repository.FloorRepository;

import lombok.Getter;
import lombok.Setter;

@CrossOrigin
@RestController
@Transactional
public class FloorRestController {
	
	@Autowired
	@Getter @Setter
	private FloorRepository floorRepository;
	
	@RequestMapping("/api/floors")
	@ResponseBody
	public List<Floor> getFloors(
			@RequestParam(value = "building", required = true) Long buildingId			
	) {		
		
		return floorRepository.findByBuilding(new Building(buildingId));	
		 
	} 

}
