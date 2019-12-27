package com.procosgroup.demo.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.procosgroup.demo.enumeration.Ownership; 

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public abstract class Location extends SpaceEntity { 
	 
	private static final long serialVersionUID = 6837759764919553623L;
	
	@Embedded
	private Address address;
	
	@Embedded
	private ContactInfo contactInfo;
		
	protected Double longitude;	
	protected Double lattitude;	
		
	@Enumerated(EnumType.STRING)
	@Column(length = 8) 
	protected Ownership ownership = Ownership.OWNED;

}
