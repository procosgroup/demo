package com.procosgroup.demo.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter @Setter
@ToString(exclude = {"attributes"})
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 6122583786019646363L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Integer version;
	
	protected String name;
	
	protected String description;
	
	private String identifier;		
	 
	@ElementCollection(targetClass = Attribute.class )
	// @MapKey(name = "name")
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
		return this.attributes.stream().filter(attribute -> attribute.getName().equals(name)).findAny().isPresent(); // collect(Collectors.toList()).isEmpty();
	}
	
	public Attribute getAttribute(String name) {
		if (attributes == null || attributes.isEmpty()) return null;
		Optional<Attribute> result = this.attributes.stream().filter(attribute -> attribute.getName().equals(name)).findFirst();		
		return result.isPresent() ? result.get() : null; 
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	} 

}
