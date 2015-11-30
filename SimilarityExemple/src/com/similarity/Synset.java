package com.similarity;

import java.util.ArrayList;
import java.util.List;

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
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }	
}
