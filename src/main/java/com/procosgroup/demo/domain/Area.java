package com.procosgroup.demo.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.procosgroup.demo.enumeration.AreaType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Area {

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 8)
	private AreaType type;
	 
	@Column(length = 4)
	private String units;	
	
	private double area;	
	private float perimeter;
	private float width;
	private float length;
	
	public Area(@NotNull AreaType type, double area) {
		super();
		this.type = type;
		this.area = area;
	} 	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Area other = (Area) obj;
		if (type != other.type)
			return false;
		return true;
	}
 
	
}
