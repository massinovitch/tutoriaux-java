package com.document;

public class PositionsInText {
	
	private int positionInPhrase;//position du mot dans la phrase
	private int positionOfPhraseWhichContainsWordInParagrphe; //numéro de la phrase auquel le mot est affecté
	private int positionOfParagrapheWhichContainsWordInDocument; //numéro du paragraphe auquel est affecté
	
	public int getPositionInPhrase() {
		return positionInPhrase;
	}
	public void setPositionInPhrase(int phrasePosition) {
		this.positionInPhrase = phrasePosition;
	}
	public int getPositionOfPhraseWhichContainsWordInParagrphe() {
		return positionOfPhraseWhichContainsWordInParagrphe;
	}
	public void setPositionOfPhraseWhichContainsWordInParagrphe(int phraseAffect) {
		this.positionOfPhraseWhichContainsWordInParagrphe = phraseAffect;
	}
	public int getPositionOfParagrapheWhichContainsWordInDocument() {
		return positionOfParagrapheWhichContainsWordInDocument;
	}
	public void setPositionOfParagrapheWhichContainsWordInDocument(int paragrapheAffect) {
		this.positionOfParagrapheWhichContainsWordInDocument = paragrapheAffect;
	}	
}
