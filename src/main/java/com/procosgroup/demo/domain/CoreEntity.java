package com.procosgroup.demo.domain;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter @Setter
@ToString
@NoArgsConstructor
public abstract class CoreEntity implements Serializable {

	private static final long serialVersionUID = 6122583786019646363L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Integer version; 
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="name_id")
	protected Localized names = new Localized();
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="description_id")
	protected Localized descriptions = new Localized(); 
	
	protected String identifier;	
	
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
		CoreEntity other = (CoreEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public CoreEntity(String name, String description) {
		super();
		setName(Locale.getDefault().getLanguage(), name);
		setDescription(Locale.getDefault().getLanguage(), description);
		//this.name = name;
		//this.description = description;
	}
	
	
	public String getName(String locale) {
	    return this.names.getString(locale);
	}

	public void setName(String locale, String name) {
	    this.names.addString(locale, name);
	}
	public String getDescription(String locale) {
	    return this.descriptions.getString(locale);
	}

	public void setDescription(String locale, String description) {
	    this.descriptions.addString(locale, description);
	}
	
	/*
	public String getName(String locale) {
		if (localizations.get(locale) == null) return null;
        return localizations.get(locale).getName();
    }
 
    public String getDescription(String locale) {
    	if (localizations.get(locale) == null) return null;
        return localizations.get(locale).getDescription();
    }
    
    public void setName(String locale, String name) {
    	if (localizations.get(locale) == null) {
    		localizations.put(locale, new LocalizedEntity(locale));
    	}
        localizations.get(locale).setName(name);
    }
 
    public void setDescription(String locale, String description) {
    	if (localizations.get(locale) == null) {
    		localizations.put(locale, new LocalizedEntity(locale));
    	}
        localizations.get(locale).setDescription(description);
    }*/
	

}
