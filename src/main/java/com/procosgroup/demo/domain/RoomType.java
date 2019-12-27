package com.procosgroup.demo.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.NoArgsConstructor;

@Entity 
@NoArgsConstructor
public class RoomType extends CoreEntity {
 
	private static final long serialVersionUID = 3376887555200069290L;
	
	@ManyToOne
	private RoomCategory roomCategory;

	public RoomType(String name, String description, RoomCategory roomCategory) {
		super(name, description);
		this.roomCategory = roomCategory;
	} 

}
