package com.procosgroup.demo.converter;

import java.util.Map;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.procosgroup.demo.domain.CoreEntity;
import com.procosgroup.demo.domain.LocalizedEntity;

public class LocalizationToStringConverter<T> extends StdConverter<Map<String, LocalizedEntity<? extends CoreEntity>>, String> {

	@Override
	public String convert(Map<String, LocalizedEntity<? extends CoreEntity>> localizations) {
		  
		return null;
	}
 
 
	

}
