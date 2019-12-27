package com.procosgroup.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class LocalizedId implements Serializable {
 
    private static final long serialVersionUID = 1089196571270403924L;
 
    @Column
    private Long id;
 
    @Column(length = 2)
    private String locale;
 
    public LocalizedId() {
    }
 
    public LocalizedId(String locale) {
        this.locale = locale;
    } 
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((locale == null) ? 0 : locale.hashCode());
        result = prime * result
                + ((id == null) ? 0 : id.hashCode());
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
        LocalizedId other = (LocalizedId) obj;
        if (locale == null) {
            if (other.locale != null)
                return false;
        } else if (!locale.equals(other.locale))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}