package com.procosgroup.demo.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@NoArgsConstructor
@Getter  @Setter
public class RoomCategory extends CoreEntity {
 
	private static final long serialVersionUID = -6714022239797126851L;
	 
	private boolean occupiable;
	
	private boolean usable;

	public RoomCategory(String name, String description, boolean occupiable, boolean usable) {
		super(name, description);
		this.occupiable = occupiable;
		this.usable = usable;
	} 
	
}
