package com.procosgroup.demo.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor 
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public abstract class LocalizedEntity<T extends CoreEntity> {
	
    @EmbeddedId
    @JsonIgnore
    protected LocalizedId localizedId;
     
    @ManyToOne
    @MapsId("localizedId.id") 
    @JoinColumn(name = "id")
    private T coreEntity;
         
    public LocalizedEntity(String locale) {
		super();
		this.localizedId = new LocalizedId(locale); 
	}

	private String name;
     
	@Column(length = 2000)
    private String description;

	@Override
	public String toString() {
		return "LocalizedEntity [ name=" + name + ", description=" + description + "]";
	} 
	/*
	public Long getId() {
		return localizedId.getId();
	}
	
	public void setId(Long id) {
		this.localizedId.setId(id);
	}*/

}
 