/*
 * Créé le 04 fevr. 2008
 *
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package com.util;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * 
 * This class allows to override the methods toString, equals and hashCode for
 * every class in the model. It uses reflection
 * 
 * @author Rachid RAMI
 * @see java.io.Serializable
 * 
 */
public class  BaseObject implements Serializable {
	/**
	 * Serial version identifier
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Specific representation for each object
	 * 
	 * @return the object representation
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * Be aware that you should identify natural keys and redefine
	 * {@link #equals(Object)} to compare properties that compose those natural
	 * keys
	 * 
	 * @return if the two objects are equals
	 * @param object
	 *            the object to compare
	 * 
	 */
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	/**
	 * Be aware that you should identify natural keys and redefine
	 * {@link #hashCode()} to compare properties that compose those natural keys
	 * 
	 * @return the hash code of the object
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
