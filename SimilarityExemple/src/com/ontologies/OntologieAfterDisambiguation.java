package com.ontologies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import rita.wordnet.jwnl.wndata.POS;

import com.document.PositionsInText;

public class OntologieAfterDisambiguation {
	
	private String name;	
	private List<ConceptInText> listConcepts;

	public OntologieAfterDisambiguation() {
		listConcepts = new ArrayList<ConceptInText>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ConceptInText> getListConcepts() {
		return listConcepts;
	}

	public void setListConcepts(List<ConceptInText> listConcepts) {
		this.listConcepts = listConcepts;
	}
	
	 
	//retourne le poids d'une ontologie dans le document
	public int getPoidsOntologie(int nbParagraphesInDocument, int typeMethod) {
		int poidsFinal = 0;
		Map<String, Map<String, Integer>> mapConceptsAndPoids = getMapConceptsAndPoids(nbParagraphesInDocument, typeMethod);
		for (Entry<String, Map<String, Integer>> entry : mapConceptsAndPoids.entrySet()) {
			Map<String, Integer> value = entry.getValue();
			int poidsInterdmediate = 0;
			for (Entry<String, Integer> e : value.entrySet()) {
				int v = e.getValue();
				poidsInterdmediate += v;
			}
			poidsFinal += poidsInterdmediate;
		}
		return poidsFinal;
	}
	
	private Map<String, Map<String, Integer>> getMapConceptsAndPoids(int nbParagraphesInDocument, int typeMethod) {
		Map<String, Map<String, Integer>> mapConceptsAndPoids = new HashMap<String, Map<String, Integer>>();
		Set<String> setNumberConcept = getSetConcepts(typeMethod);//Récupérer la liste des concepts du document pour cette ontologie
		Iterator<String> iteratorSetNumberConcept = setNumberConcept.iterator();
		while(iteratorSetNumberConcept.hasNext()) {
	    	String currentConceptNumber = iteratorSetNumberConcept.next();//Pour chaque concept, on va calculer sa fréquence d'apparition par paragraphe et le multiplier aux autre concept
	    	Set<String> setConceptsWithoutElementInParameter = getSetConceptsClone(setNumberConcept);
	    	for (int i = 1; i <= nbParagraphesInDocument; i++) {
	    		int nbConceptInParagrapheForCurrentConcept = getNbConceptInParagraphe(currentConceptNumber, i);//nombre d'occurence de currentConceptNumber dans le paragraphe i
	    		Iterator<String> iteratorsetConceptsWithoutElementInParameter = setConceptsWithoutElementInParameter.iterator();
	    		while(iteratorsetConceptsWithoutElementInParameter.hasNext()) {
	    			String otherConceptNumber = iteratorsetConceptsWithoutElementInParameter.next();//les autres concepts, on les parcourt un par un
	    			int result;
    				int nbConceptInParagrapheForOtherConcept = getNbConceptInParagraphe(otherConceptNumber, i);
	    			if (otherConceptNumber.equals(currentConceptNumber)) {//les deux concepts sont egaux, le poid est egal à 1 si le concept existe dans le paragraphe
	    				if ( nbConceptInParagrapheForOtherConcept > 0 ) {
		    				result = 1;	    					
	    				} else {
	    					result = 0;
	    				}
	    			} else {
		    			result = nbConceptInParagrapheForCurrentConcept * nbConceptInParagrapheForOtherConcept;//le resultat du poids entre le concept courant et l'autre concept par paragraphe	    				
	    			}
	    			Map<String, Integer> mapResultConceptCourant = mapConceptsAndPoids.get(currentConceptNumber);
	    			if ( mapResultConceptCourant != null ) {// la map a été déja créer
	    				if (mapResultConceptCourant.get(otherConceptNumber) != null) {
		    				int oldResult = mapResultConceptCourant.get(otherConceptNumber);
		    				result += oldResult;	    					    					
	    				}
	    			} else {
	    				mapResultConceptCourant = new HashMap<String, Integer>();
	    				mapConceptsAndPoids.put(currentConceptNumber, mapResultConceptCourant);
	    			}
    				mapResultConceptCourant.put(otherConceptNumber, result);
	    		}
	    	}

		}
		return mapConceptsAndPoids;
	}
	
	//renvoyer l'ensemble des concepts  (leur numéros, ils ne sont pas dupliqués)
	private Set<String> getSetConcepts(int typeMethod) {
		Set<String> setNumberConcept = new HashSet<String>();
		for ( int i = 0; i < listConcepts.size(); i++) {
			ConceptInText conceptInText = listConcepts.get(i);
			POS typeMot = conceptInText.getConceptJwnl().getType();
			if ( typeMethod == 1 ) {
				if  (!typeMot.equals(POS.ADVERB)) {//ignorer les adverbes du calcul de score
					setNumberConcept.add(conceptInText.getConceptJwnl().getNumber());			
				}
			} else {//methode deux, ne prendre que les noms
				if (typeMot.equals(POS.NOUN)) {
					setNumberConcept.add(conceptInText.getConceptJwnl().getNumber());								
				}
			}
			
		}
		return setNumberConcept;
	}
	
	//renvoyer l'ensemble des concepts
	private Set<String> getSetConceptsClone(Set<String> setNumberConcept) {
		Set<String> setNumberConceptResult = new HashSet<String>();
		Iterator<String> iterator = setNumberConcept.iterator();
		while(iterator.hasNext()) {
	    	String currentConceptNumber = iterator.next();
        	setNumberConceptResult.add(currentConceptNumber);
		}
		return setNumberConcept;
	}
	
	private int getNbConceptInParagraphe(String numConcept, int numParagraphe) {
		int cptNbConceptInParagraphe = 0;
		for ( int i = 0; i < listConcepts.size(); i++) {
			ConceptInText conceptInText = listConcepts.get(i);
			int currentNumParagrpahe = conceptInText.getPositionInText().getPositionOfParagrapheWhichContainsWordInDocument();
			if (numParagraphe == currentNumParagrpahe) {//si on est dans le bon paragraphe
				String currentNumConcept = conceptInText.getConceptJwnl().getNumber();
				if ( currentNumConcept.equals(numConcept)) {
					cptNbConceptInParagraphe++;
				}				
			}
		}		
		return cptNbConceptInParagraphe;
	}
	
	public void displayConcept(int indexConceptsInText) {
		ConceptInText coneceptInText = listConcepts.get(indexConceptsInText);
		ConceptJwnl conceptJwnl = coneceptInText.getConceptJwnl();
		PositionsInText positionInText = coneceptInText.getPositionInText();
	}	
}
