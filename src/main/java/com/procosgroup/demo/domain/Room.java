package com.procosgroup.demo.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter 
@NoArgsConstructor
public class Room extends SpaceEntity {

	private static final long serialVersionUID = 8288740120806706438L;
		
	@ManyToOne
	private Floor floor;
	
	@ManyToOne
	private RoomType roomType;
	
	private Integer capacity;
		
	@Access(AccessType.PROPERTY)  
	@Basic(optional = true)
	public Building getBuilding( ) {
		return (floor != null) ? floor.getBuilding() : null;
 	}
	
	public void setBuilding(Building building) {
		if (floor == null) {
			floor = new Floor(building);
		} else {
			floor.setBuilding(building);
		}
 	} 

}
