package com.procosgroup.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.procosgroup.demo.enumeration.DataType;

@Entity
public class AttributeDefinition extends CoreEntity {

	private static final long serialVersionUID = -7936795653024864913L;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 8)
	private DataType dataType;
	
	

}
