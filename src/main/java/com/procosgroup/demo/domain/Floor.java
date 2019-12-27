package com.procosgroup.demo.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
public class Floor extends SpaceEntity {
 
	private static final long serialVersionUID = 7586780438071883115L;
	
	@ManyToOne
	private Building building;

	public Floor(Building building) {
		super();
		this.building = building;
	}

	public Floor(Long floorId) {
		setId(floorId);
	} 

}
