package com.procosgroup.demo.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
	
	private String contactName;
	private String telephone;
	private String fax;
	private String contactEmail;
	private String url;	 
	
}
