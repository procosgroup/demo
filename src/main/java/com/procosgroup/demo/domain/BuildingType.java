package com.procosgroup.demo.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
public class BuildingType extends CoreEntity {
	
	private static final long serialVersionUID = 3697869307617577936L;
			
	@OneToMany(mappedBy = "buildingType", cascade = {CascadeType.ALL}, orphanRemoval = true)
	@MapKey(name = "localizedId.locale")
	@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)	
	@JsonManagedReference
	@JsonIgnoreProperties({"buildingType","coreEntity"}) 
	private Map<String, BuildingTypeLang> localizations;
	
	public BuildingType(String name, String description) {
		super(name, description); 
	}
		
	@Override
	public String getName(String locale) {
		if (localizations == null || localizations.get(locale) == null) return null;
        return localizations.get(locale).getName();
    }
 
	@Override
    public String getDescription(String locale) {
    	if (localizations == null || localizations.get(locale) == null) return null;
        return localizations.get(locale).getDescription();
    }
    
	@Override
    public void setName(String locale, String name) {
		initLocalizations(locale);
        localizations.get(locale).setName(name);
    }
 
	@Override
    public void setDescription(String locale, String description) {
		initLocalizations(locale);
        localizations.get(locale).setDescription(description);
    }
	
	private void initLocalizations(String locale) {
		if (localizations == null) {
			localizations = new HashMap<>();
		}
		if (!localizations.containsKey(locale)) {
    		localizations.put(locale, new BuildingTypeLang(locale));
    	}
	}
	
	

}
