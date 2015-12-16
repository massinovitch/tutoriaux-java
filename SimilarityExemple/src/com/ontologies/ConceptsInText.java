package com.ontologies;

import java.util.ArrayList;
import java.util.List;

import rita.wordnet.jwnl.JWNLException;
import rita.wordnet.jwnl.wndata.POS;



import com.document.PositionsInText;
import com.jwnl.WordnetHelp;
//contient tout les concept renvoyés pour un terme, utilisé avant la phase de désambiguisation
import com.properties.Commun;
import com.properties.DistanceConcept;
public class ConceptsInText {
	private List<ConceptJwnl> listConceptJwnl;
	private PositionsInText positionInText;
	private POS type;//type des mots qui sont dans listConceptJwnl
	private int lengthConcept;//longueur des concepts gardés
	
	public ConceptsInText() {
		listConceptJwnl = new ArrayList<ConceptJwnl>();
	}
	public List<ConceptJwnl> getListConceptJwnl() {
		return listConceptJwnl;
	}

	public void setListConceptJwnl(List<ConceptJwnl> listConceptJwnl) {
		this.listConceptJwnl = listConceptJwnl;
	}

	public PositionsInText getPositionInText() {
		return positionInText;
	}

	public void setPositionInText(PositionsInText positionInText) {
		this.positionInText = positionInText;
	}

	public boolean isAmbigue() {
		return ( listConceptJwnl.size() >  1);
	}
	
	public POS getType() {
		return type;
	}
	
	public void setType(POS type) {
		this.type = type;
	}
	
	public int getLengthConcept() {
		return lengthConcept;
	}

	public void setLengthConcept(int lengthConcept) {
		this.lengthConcept = lengthConcept;
	}

	public String getKey() {//la clé unique de conceptsInText
		String key = positionInText.getPositionInPhrase() + "_" + positionInText.getPositionOfPhraseWhichContainsWordInParagrphe() + "_" + positionInText.getPositionOfParagrapheWhichContainsWordInDocument();
		return key;
	}
	
	//renvoyer la distance minimum du voisin v
	public DistanceConcept getDistanceMin(ConceptJwnl v) throws JWNLException {
		int conceptJwnlSize = getListConceptJwnl().size();//nombre d'element dans le concept à desambiquiser
		DistanceConcept[] distanceBetweenConcepts = new DistanceConcept[conceptJwnlSize];//contient la distance entre chaque concept et le concept voisin gauche
		for (int i = 0; i < conceptJwnlSize; i++ ) {//calculer la disantce entre chaque concept et le voisin selectionné
			ConceptJwnl conceptJwnl = getListConceptJwnl().get(i);
			float distance = WordnetHelp.getDistance(v, conceptJwnl);
			DistanceConcept distanceConcept = new DistanceConcept();
			distanceConcept.setIndex(i);
			distanceConcept.setValue(distance);
			distanceBetweenConcepts[i] = distanceConcept;							
		}
		DistanceConcept distanceMin = Commun.min(distanceBetweenConcepts);
		return distanceMin;
	}
	
	public boolean keepMinConcept(ConceptJwnl conceptJwnlVg, ConceptJwnl conceptJwnlVd) throws JWNLException {
		boolean result = false;
		DistanceConcept distanceConceptSelected = null;
		if ( (conceptJwnlVg != null) && (conceptJwnlVd != null) ) {
			DistanceConcept disanceVdConcept = getDistanceMin(conceptJwnlVd);
			DistanceConcept disanceVgConcept = getDistanceMin(conceptJwnlVg);			
			if ( disanceVdConcept.getValue() < disanceVgConcept.getValue() ) {
				distanceConceptSelected = disanceVdConcept;
				result = true;
			} else if ( disanceVdConcept.getValue() > disanceVgConcept.getValue() ){
				distanceConceptSelected = disanceVgConcept;
				result = true;
			}
		} else if (conceptJwnlVg != null) {
			distanceConceptSelected = getDistanceMin(conceptJwnlVg);
			if ( distanceConceptSelected.getValue() != 1.0) {
				result = true;
			}
		} else if (conceptJwnlVd != null) {
			distanceConceptSelected = getDistanceMin(conceptJwnlVd);
			if ( distanceConceptSelected.getValue() != 1.0) {
				result = true;
			}
		}
		if ( result ) {
			ConceptJwnl selectedConceptJwnl = getListConceptJwnl().get(distanceConceptSelected.getIndex());//selection l'elemeent le plus proche du voisin non ambigue
			List<ConceptJwnl> newListConceptJwnl = new ArrayList<ConceptJwnl>();
			newListConceptJwnl.add(selectedConceptJwnl);
			setListConceptJwnl(newListConceptJwnl);//			
		}
		return result;
	}
	
	//selectionner 1er concept pour les concepts de type autre que noun.
	public void selectFirstConcept() {
		ConceptJwnl selectedConceptJwnl = getListConceptJwnl().get(0);//selection du premier element
		List<ConceptJwnl> newListConceptJwnl = new ArrayList<ConceptJwnl>();
		newListConceptJwnl.add(selectedConceptJwnl);
		setListConceptJwnl(newListConceptJwnl);//		
	}
}
