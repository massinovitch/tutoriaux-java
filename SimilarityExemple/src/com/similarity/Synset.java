package com.similarity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class Synset implements Cloneable {
	
	private String numero;
	private String nom;
	private List<String> listNumeroBranche;
	
	public Synset() {
		listNumeroBranche = new ArrayList<String>();
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<String> getListNumeroBranche() {
		return listNumeroBranche;
	}
	public void setListNumeroBranche(List<String> listNumeroBranche) {
		this.listNumeroBranche = listNumeroBranche;
	}	
	
	@Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    Synset other = (Synset) obj;
	    return new EqualsBuilder().append(numero, other.numero).isEquals();//mettre les champs à tester dans l'égalité.
	  }
	 
	  @Override
	  public int hashCode() {
	    return new HashCodeBuilder(17, 31).append(numero).toHashCode();//17, 31 sont choisis de façon aléatoire(deux entiers impairs)
	  }	
    
	@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }	
}
