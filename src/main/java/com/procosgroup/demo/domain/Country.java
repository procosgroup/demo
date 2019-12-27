package com.procosgroup.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Country {
	
	@Id
	@Column(length=2)
	private String countryCode;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="name_id")
	@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
	private Localized names = new Localized();

	public Country(String countryCode) {
		super();
		this.countryCode = countryCode;
	}
	
	public String getName(String locale) {
	    return this.names.getString(locale);
	}

	public void setName(String locale, String name) {
	    this.names.addString(locale, name);
	}		

}
