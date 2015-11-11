package com.ontologies;

import com.document.PositionsInText;

//représente un concept dans le text, sera utilisé après la phase de désambiguisation
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
