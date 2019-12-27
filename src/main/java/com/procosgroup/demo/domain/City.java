package com.procosgroup.demo.domain;

import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter @Getter
public class City extends CoreEntity {
 
	private static final long serialVersionUID = -5472758485679732975L;

	@ManyToOne
	@JoinColumn(name = "country_code")
	private Country country;

	public City(Country country, String name) {
		super();
		this.country = country;
		this.setName(Locale.getDefault().getLanguage(), name); 
	}
	
	
	
}
