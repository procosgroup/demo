package com.procosgroup.demo.rest;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.procosgroup.demo.domain.Floor;
import com.procosgroup.demo.domain.Room;
import com.procosgroup.demo.domain.RoomCategory;
import com.procosgroup.demo.domain.RoomType;
import com.procosgroup.demo.repository.RoomCategoryRepository;
import com.procosgroup.demo.repository.RoomRepository;
import com.procosgroup.demo.repository.RoomTypeRepository;  

import lombok.Getter;
import lombok.Setter;

@CrossOrigin
@RestController
@Transactional
public class RoomRestController {
	
	@Autowired
	@Setter @Getter
	private RoomRepository roomRepository;
	
	@Autowired
	@Setter @Getter
	private RoomTypeRepository roomTypeRepository;
	
	@Autowired
	@Setter @Getter
	private RoomCategoryRepository roomCategoryRepository;
	
	@RequestMapping("/api/room/cats")
	@ResponseBody
	public Iterable<RoomCategory> getRoomCategories() {		
		return roomCategoryRepository.findAll();	 
	} 	
	
	@RequestMapping("/api/room/types")
	@ResponseBody
	public Iterable<RoomType> getRoomTypes() {		
		return roomTypeRepository.findAll();		 
	} 
	
	@RequestMapping("/api/rooms")
	@ResponseBody
	public List<Room> getRooms( 
			@RequestParam(value = "floor", required = true) Long floorId			
	) {		
		return roomRepository.findByFloor(new Floor(floorId));			 
	} 	

}
