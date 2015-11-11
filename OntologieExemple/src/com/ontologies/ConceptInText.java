package com.ontologies;

import com.document.PositionsInText;

//repr�sente un concept dans le text, sera utilis� apr�s la phase de d�sambiguisation
public class ConceptInText {
	private ConceptJwnl conceptJwnl;
	private PositionsInText positionInText;
	
	public ConceptJwnl getConceptJwnl() {
		return conceptJwnl;
	}
	public void setConceptJwnl(ConceptJwnl conceptJwnl) {
		this.conceptJwnl = conceptJwnl;
	}
	public PositionsInText getPositionInText() {
		return positionInText;
	}
	public void setPositionInText(PositionsInText positionInText) {
		this.positionInText = positionInText;
	}
}
