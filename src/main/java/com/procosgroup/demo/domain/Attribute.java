package com.procosgroup.demo.domain;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@NoArgsConstructor
public class Attribute {
	
	private String name;
	private String stringValue;
	private Double numericValue;
	
	@Temporal(TemporalType.DATE)
	private Date dateValue;
	
	public Attribute(String name, Object value) { 
		this.name = name;
		
		if (value instanceof String) {
			stringValue = (String) value;
		} else if (value instanceof Date) {
			dateValue = (Date) value;
		} else {
			numericValue = new Double(value.toString());
		}
	}	
	
	public Object getValue() {
		if  (stringValue != null) return stringValue; 
		if  (numericValue != null) return numericValue; 
		if  (dateValue != null) return dateValue; 
		return null;
	}
	
}
