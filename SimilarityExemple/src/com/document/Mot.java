package com.document;

import rita.wordnet.jwnl.wndata.POS;


public class Mot {
	
	private String value;
	private String base;//base ou racine du mot. exemple : eats, son racine est : eat
	private POS type;//type du mot, noun, verb ...
	private PositionsInText positionInText;

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}		
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public POS getType() {
		return type;
	}
	public void setType(POS type) {
		this.type = type;
	}
	public PositionsInText getPositionInText() {
		return positionInText;
	}
	public void setPositionInText(PositionsInText positionInText) {
		this.positionInText = positionInText;
	}	
}
