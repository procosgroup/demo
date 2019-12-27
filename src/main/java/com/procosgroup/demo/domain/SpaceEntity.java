package com.procosgroup.demo.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.procosgroup.demo.enumeration.AreaType;
import com.procosgroup.demo.enumeration.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter @Setter
@ToString(exclude = {"attributes"})
@EntityListeners(AuditingEntityListener.class)
public abstract class SpaceEntity implements Serializable {

	private static final long serialVersionUID = 6122583786019646363L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Version
	private Integer version;
	
	protected String name;
	
	protected String description;
	
	protected String identifier;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 8) 
	protected Status status = Status.ACTIVE;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate 
    protected Date createdDate;
 
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modifiedDate;
    
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;
 
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;
		
	@ElementCollection
	private Collection<Area> areas;
	
	public void addArea(Area area) {
		if (areas == null) {
			areas = new HashSet<>();
		}
		areas.add(area);
	}
	
	public Optional<Area> getArea(AreaType areaType) {
		if (areas == null || areas.isEmpty()) return Optional.empty();		
		return areas.stream().filter(area -> area.getType() == areaType).findFirst();
	}
	
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
		SpaceEntity other = (SpaceEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
