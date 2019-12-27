package com.procosgroup.demo.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class WorkPlace extends SpaceEntity {

	private static final long serialVersionUID = -2867361924560884256L;
	
	@ManyToOne
	private Room room;
	
	private boolean occupied;
	
	private String occupiedBy;
	
	private String department;

}
