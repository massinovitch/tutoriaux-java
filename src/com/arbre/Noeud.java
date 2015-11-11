package com.arbre;

import java.util.ArrayList;
import java.util.List;

public class Noeud {
	private String synset;
	private String branche;
	private int niveau;
	private List<String> listFils;
	
	public Noeud() {
		listFils = new ArrayList<String>();
		niveau = 0;
	}
	
	public String getSynset() {
		return synset;
	}
	public void setSynset(String synset) {
		this.synset = synset;
	}
	public String getBranche() {
		return branche;
	}
	public void setBranche(String branche) {
		this.branche = branche;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int position) {
		this.niveau = position;
	}
	public List<String> getListFils() {
		return listFils;
	}
	public void setListFils(List<String> listFils) {
		this.listFils = listFils;
	}
}
