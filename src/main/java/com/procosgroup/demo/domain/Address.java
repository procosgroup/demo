package com.procosgroup.demo.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Address {
	 
	@ManyToOne
	@JoinColumn(name = "country_code")
	private Country country;
	
	private String postalCode;
	
	@ManyToOne
	private City city;
	
	private String street;
	private String number;
	
	public Address(Country country,  City city, String postalCode, String street, String number) {	 
		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
		this.postalCode = postalCode;
	} 

}
