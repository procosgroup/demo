package com.procosgroup.demo.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection; 
import javax.persistence.Entity;
import javax.persistence.FetchType; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@Entity
@ToString
public class Localized {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty
    private int id; 
    
    @JsonProperty
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "localized_string", joinColumns = @JoinColumn(referencedColumnName = "id"))
    @MapKeyColumn(name = "locale", length = 5)
    @Column(name = "text")
    private Map<String,String> strings = new HashMap<String, String>();

    //private String locale;    
    //private String text;

    public Localized() {}

    public Localized(Map<String, String> map) {
        this.strings = map;
    }

    public void addString(String locale, String text) {
        strings.put(locale, text);
    }

    public String getString(String locale) {
        String returnValue = strings.get(locale);
        return (returnValue != null ? returnValue : null);
    }

}
