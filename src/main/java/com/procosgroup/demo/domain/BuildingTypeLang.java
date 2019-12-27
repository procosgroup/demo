package com.procosgroup.demo.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@Entity
@NoArgsConstructor 
@Getter @Setter 
public class BuildingTypeLang extends LocalizedEntity<BuildingType>{
 	
	@ManyToOne
    @MapsId("localizedId.id")
    @JoinColumn(name = "id")
	@JsonIgnore
	@JsonBackReference
    private BuildingType buildingType;
         
	public BuildingTypeLang(String locale) {
		super(locale); 
	}  

}
