package com.procosgroup.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.procosgroup.demo.enumeration.Ownership;
import com.procosgroup.demo.enumeration.Status;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor 
public class Building extends Location {

	private static final long serialVersionUID = 8593383174977274380L;
	
	@ManyToOne
	private BuildingType buildingType; 

	public Building(Long buidlingId) {
		super();
		this.setId(buidlingId);
	} 
	
	public Building(String identifier) {
		super();
		this.setIdentifier(identifier);
	}
	
	public Building(String identifier, Status status, Ownership ownership) {
		super();
		this.setIdentifier(identifier);
		this.status = status;
		this.ownership = ownership;
	} 

	public Building(BuildingType buildingType, Status status, Ownership ownership) {
		super();
		this.buildingType = buildingType;
		this.status = status;
		this.ownership = ownership;
	}  
	
}
