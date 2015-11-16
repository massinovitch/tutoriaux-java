package com.ontologies;

import com.properties.GeneralConstants;

import rita.wordnet.jwnl.wndata.POS;

public class ConceptJwnl implements Cloneable, Comparable<ConceptJwnl> {
		
	private String number;//numéro du concept
	private String terme;//le concept lui meme
	private POS type;
	private String nameOntologie; //le nom de l'ontologie auquel appartient ce concept
	private int length;//nombre de mot qui compose le terme (les mots sont séparés par '_'
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTerme() {
		return terme;
	}
	public void setTerme(String terme) {
		this.terme = terme;
	}			
	public POS getType() {
		return type;
	}
	public void setType() {
		if ( number.endsWith(GeneralConstants.SUFFIXE_ADJECTIF) ) {
			this.type = POS.ADJECTIVE;			
		} else if ( number.endsWith(GeneralConstants.SUFFIXE_ADVERB) ) {
			this.type = POS.ADVERB;
		} else if ( number.endsWith(GeneralConstants.SUFFIXE_NOUN) ) {
			this.type = POS.NOUN;
		} else if ( number.endsWith(GeneralConstants.SUFFIXE_VERB) ) {
			this.type = POS.VERB;
		} else {
			System.out.println("");
		}
	}
	public String getNameOntologie() {
		return nameOntologie;
	}
	public void setNameOntologie(String nameOntologie) {
		this.nameOntologie = nameOntologie;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}		
	
	public int compareTo(ConceptJwnl c) {
		if ( this.length < c.length ){
			return -1;
		} else if (this.length == c.length) {
			return 0;			
		} else {
			return 1;
		}
	}
	
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
}
