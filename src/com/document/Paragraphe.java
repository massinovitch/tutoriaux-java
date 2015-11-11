package com.document;

import java.util.ArrayList;
import java.util.List;

import rita.wordnet.jwnl.JWNLException;

import com.properties.GeneralConstants;

public class Paragraphe {
	
	private String value;
	private int positionInDocument;//position du paragraphe dans le document
	private List<Phrase> listPhrases; //list des phrases de ce paragraphe
		
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getPositionInDocument() {
		return positionInDocument;
	}
	public void setPositionInDocument(int positionDocument) {
		this.positionInDocument = positionDocument;
	}
	public List<Phrase> getListPhrases() {
		return listPhrases;
	}
	public void setListPhrases(List<Phrase> listPhrases) {
		this.listPhrases = listPhrases;
	}
	public void loadListPhrases() throws JWNLException {
		listPhrases = new ArrayList<Phrase>();
		String[] phrases = value.split(GeneralConstants.SEPARATEUR_ENTRE_PHRASES);
		for (int i = 0; i < phrases.length; i++) {
			Phrase phrase = new Phrase();
			phrase.setPositionOfParagrapheWhichContainsWordInDocument(positionInDocument);
			phrase.setPositonInParagraph(i + 1);
			phrase.setValue(phrases[i].trim());
			phrase.loadListMots();
			listPhrases.add(phrase);
		}
	}
	
	
}
