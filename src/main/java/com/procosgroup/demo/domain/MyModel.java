package com.procosgroup.demo.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class MyModel {
	
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  
}