package com.procosgroup.demo.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

@Embeddable
public class AttributeCollection {
	
	@ElementCollection( targetClass = Attribute.class ) 
	private Collection<Attribute> attributes; 
	
	public void addAttributes(Attribute attribute) {
		if (attributes == null) {
			attributes = new HashSet<>();					
		}
		attributes.add(attribute);
	}
	
	public boolean hasAttribute(String name) {
		if (attributes == null || attributes.isEmpty()) return false;
		// return attributes.containsKey(name);
		return ! this.attributes.stream().filter(attribute -> attribute.getName().equals(name)).collect(Collectors.toList()).isEmpty();
	}
	
	public Attribute getAttribute(String name) {
		if (attributes == null || attributes.isEmpty()) return null;
		List<Attribute> atts = this.attributes.stream().filter(attribute -> attribute.getName().equals(name)).collect(Collectors.toList());
		
		return !atts.isEmpty() ? atts.get(0) : null; 
	}
	
	public Object getValue(String name) {
		if (attributes == null || attributes.isEmpty()) return null;
		Attribute attribute = getAttribute(name); 
		return  attribute != null ? attribute.getValue() : null;
	}
	
	public String getStringValue(String name) {
		if (attributes == null || attributes.isEmpty()) return null;
		Attribute attribute = getAttribute(name); 
		return  attribute != null ? attribute.getStringValue() : null;
	}
	
	public Date getDateValue(String name) {
		if (attributes == null || attributes.isEmpty()) return null;
		Attribute attribute = getAttribute(name); 
		return  attribute != null ? attribute.getDateValue() : null;
	}
	
	public Integer getIntValue(String name) {
		if (attributes == null || attributes.isEmpty()) return null;
		Attribute attribute = getAttribute(name); 
		return  attribute != null ? attribute.getNumericValue() != null ? attribute.getNumericValue().intValue() : null  : null; 
	}
	
	public Double getNumericValue(String name) {
		if (attributes == null || attributes.isEmpty()) return null;
		Attribute attribute = getAttribute(name); 
		return  attribute != null ? attribute.getNumericValue() != null ? attribute.getNumericValue()  : null : null; 
	}
}
