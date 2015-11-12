package com.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import rita.wordnet.jwnl.JWNLException;

import com.document.FileDocument;
import com.document.Mot;
import com.document.Paragraphe;
import com.document.Phrase;
import com.jwnl.WordnetHelp;
import com.ontologies.ConceptInText;
import com.ontologies.ConceptJwnl;
import com.ontologies.ConceptsInText;
import com.ontologies.OntologieAfterDisambiguation;
import com.ontologies.OntologieBeforeDisambiguation;
import com.properties.Commun;
import com.properties.Constants;
import com.properties.Distance2Concepts;
import com.properties.GeneralConstants;
import com.properties.LevelSearch;
import com.wordnet.FileWordnet;


public class Principal {
	//List<Concept> listConceptDescriminant;
	//List<Concept> listConceptAmigu;
	
	//regarde si il existe encore un terme ambigue dans une liste de terme d'une ontologie
	public boolean existeTermeAmbigueInOntologie(List<ConceptsInText> listConceptsInText) {		
		for (int j = 0; j < listConceptsInText.size(); j++) {
			ConceptsInText coneceptsInText = listConceptsInText.get(j);
			if (coneceptsInText.isAmbigue()) {
				return true;
			}
		}
		return false;
	}
	
	//renoi la liste des termes ambigue
	public List<ConceptsInText> getListTermesAmbigue(List<ConceptsInText> listConceptsInText) {
		List<ConceptsInText> listResult = new ArrayList<ConceptsInText>();
		for (int j = 0; j < listConceptsInText.size(); j++) {
			ConceptsInText coneceptsInText = listConceptsInText.get(j);
			if (coneceptsInText.isAmbigue()) {
				listResult.add(coneceptsInText);
			}
		}
		return listResult;
	}
	
	//renvoyer la distance entre deux terme
	public Distance2Concepts getDistanceMinBetween2Termes(ConceptsInText conceptsInText1, ConceptsInText conceptsInText2, int positionConceptInListAmbigu) throws JWNLException {
		int length1 = conceptsInText1.getListConceptJwnl().size();
		int length2 = conceptsInText2.getListConceptJwnl().size();	
		Distance2Concepts [][] tab = new Distance2Concepts [length1][length2];
		for (int i = 0; i < length1; i++) {
			for (int j = 0; j < length2; j++) {
				ConceptJwnl conceptJwnli = conceptsInText1.getListConceptJwnl().get(i);
				ConceptJwnl conceptJwnlj = conceptsInText2.getListConceptJwnl().get(j);				
				float distance = WordnetHelp.getDistance(conceptJwnli, conceptJwnlj);
				System.out.println("				Le distance entre " + conceptJwnli.getTerme() + " " + conceptJwnli.getNumber() + " et " + conceptJwnlj.getTerme() + " " + conceptJwnlj.getNumber() + " est : " + distance);
				Distance2Concepts distanceConcept = new Distance2Concepts();
				distanceConcept.setIndex1(i);
				distanceConcept.setIndex2(j);				
				distanceConcept.setValue(distance);
				tab[i][j] = distanceConcept;	
			}
		}
		Distance2Concepts distanceMin = Commun.min(tab);
		distanceMin.setPositionConceptInListAmbigu(positionConceptInListAmbigu);
		return distanceMin;		

	}

	//désambiguise 1 ou 2 termes ambigue
	public void desambiguisterListeTermesAmbigue(List<ConceptsInText> listConceptsInText) throws JWNLException {
		if (listConceptsInText.size() == 1) {//si il reste un terme ambigue, renvoyer le premier synset de son concept
			ConceptsInText conceptsInText = listConceptsInText.get(0);
			ConceptJwnl selectedConceptJwnl = conceptsInText.getListConceptJwnl().get(0);//selection l'elemeent le plus proche du voisin non ambigue
			List<ConceptJwnl> newListConceptJwnl = new ArrayList<ConceptJwnl>();
			newListConceptJwnl.add(selectedConceptJwnl);
			conceptsInText.setListConceptJwnl(newListConceptJwnl);//	
			System.out.println("		Terme : \"" + selectedConceptJwnl.getTerme() + " " + selectedConceptJwnl.getNumber() + " a été selectionné");
		} else if ( listConceptsInText.size() > 1 ) {//nombre terme ambigue superieur à 2. désambiguiser au moins de termes
			int length = listConceptsInText.size() - 1;
			Distance2Concepts [] tabDistance = new Distance2Concepts [length];//tableau contenant les distance minimum entre (le terme i et i+1)
			for (int i = 0; i < length; i++) {
				ConceptsInText conceptsInText1 = listConceptsInText.get(i);
				ConceptsInText conceptsInText2 = listConceptsInText.get(i+1);
				Distance2Concepts distanceMinBetween2Termes = getDistanceMinBetween2Termes(conceptsInText1, conceptsInText2, i);
				tabDistance[i] = distanceMinBetween2Termes;
			}
			Distance2Concepts distanceMin = Commun.min(tabDistance);
			ConceptsInText conceptsInText1 = listConceptsInText.get(distanceMin.getPositionConceptInListAmbigu());
			ConceptJwnl conceptJwnl1 = conceptsInText1.getListConceptJwnl().get(distanceMin.getIndex1());//selection l'elemeent le plus proche du voisin non ambigue
			List<ConceptJwnl> newListConceptJwnl1 = new ArrayList<ConceptJwnl>();
			newListConceptJwnl1.add(conceptJwnl1);
			conceptsInText1.setListConceptJwnl(newListConceptJwnl1);
			ConceptsInText conceptsInText2 = listConceptsInText.get(distanceMin.getPositionConceptInListAmbigu() + 1);
			System.out.println("		Terme : \"" + conceptJwnl1.getTerme() + " " + conceptJwnl1.getNumber() + " a été selectionné");
			ConceptJwnl conceptJwnl2 = conceptsInText2.getListConceptJwnl().get(distanceMin.getIndex2());//selection l'elemeent le plus proche du voisin non ambigue
			List<ConceptJwnl> newListConceptJwnl2 = new ArrayList<ConceptJwnl>();
			newListConceptJwnl2.add(conceptJwnl2);
			conceptsInText2.setListConceptJwnl(newListConceptJwnl2);//	
			System.out.println("		Terme : \"" + conceptJwnl2.getTerme() + " " + conceptJwnl2.getNumber() + " a été selectionné");						
		}
	}

	
	public static void main(String[] args) throws IOException, JWNLException, CloneNotSupportedException {				       		      
		// Initialize the database
        // You must configure the properties file to point to your dictionary files
        WordnetHelp.initialize("resources/file_properties.xml");
        //System.out.println("SimilarTo distance summary<->synopsis: "+WordnetHelp.distance("summary", POS.NOUN, "synopsis", POS.NOUN, PointerType.SIMILAR_TO));
		//int distance = WordnetHelp.distance("tree", POS.NOUN, "trunk", POS.NOUN, PointerType.HYPERNYM);
        //System.out.println("Choisir Méthode de désambiguisation (1 : désambiguiser les noms par calcul de distance, 2 : désambiguiser en prenant le premier nom de la liste).");
		int typeMethodDesambiguisation = 1;//Commun.getInt() renvoi un entier 1 ou 2		
        //System.out.println("Choisir Méthode de Calcul de score (1 : noms + adjectifs + verbes, 2 : noms uniquement). ");
		int typeMethodCalculScore = 2;//renvoi un entier 1 ou 2		
		FileWordnet fileWordnet = new FileWordnet();
		FileDocument fileDocument = new FileDocument();
		String fileNameDocument = Constants.getInstance().getProperty("fileName.document");//le document qui va etre lu et traité par notre algo
		List<Paragraphe> listParagrphes = fileDocument.getListParagraphes(fileNameDocument);
		Map<String, OntologieBeforeDisambiguation> mapOntologiesBeforeDisambiguation = fileWordnet.getMapOntologieBeforeDisambiguation();
		List<OntologieAfterDisambiguation> listOntologiesAfterDisambiguation = new ArrayList<OntologieAfterDisambiguation>();
		Principal principal = new Principal();

		for (int i = 0; i < listParagrphes.size(); i++) {
			Paragraphe currentParagraphe = listParagrphes.get(i);
			List<Phrase> listPhrases = currentParagraphe.getListPhrases();
			for (int j = 0; j < listPhrases.size(); j++) {
				Phrase currentPhrase = listPhrases.get(j);
				List<Mot> listMots = currentPhrase.getListMots();
				int k = 0;
				while ( k < listMots.size() ) {
					Mot currentMot = listMots.get(k);
					ConceptsInText listConceptsForMot = fileWordnet.getListConceptBeginWithMotAndEqualWithTerme(currentPhrase, currentMot);//récupérer les concepts qui sont présents entièrement dans la phrase et qui appartiennent aux ontologies passées en entrée.
					if (listConceptsForMot.getListConceptJwnl().size() > 0) {//aucun concept ne correspond au mot recherché. passer au mot suivant.				
						Map<String, ConceptsInText> mapTallerConceptsByOntologie = fileWordnet.getTallerConcepts(listConceptsForMot);//list des concept pour un mot	par ontologie
						for(Entry<String, ConceptsInText> entry : mapTallerConceptsByOntologie.entrySet()) {//rajouter les concepts trouvés au bonnes ontologies
							String key = entry.getKey();
							ConceptsInText value = entry.getValue();
							OntologieBeforeDisambiguation ontologieBeforeDisambiguation = mapOntologiesBeforeDisambiguation.get(key);
							ontologieBeforeDisambiguation.getListConcepts().add(value);
						}
						k = k + listConceptsForMot.getLengthConcept();
					} else {//pas de concept pour ce mot, passer au mot suivant
						k++;						
						if ( currentMot.getValue().equalsIgnoreCase(GeneralConstants.IN) ) {
							Mot nextMot = listMots.get(k);
							if (nextMot.getValue().equalsIgnoreCase(GeneralConstants.ORDER)) {
								k++;
							}
						}
					}					
				}
			}
		}
		
		System.out.println("1. Liste des concepts avant désambiguisation : ");
		//traiter ontologie par ontologie, niveau phrase
		for (Entry<String, OntologieBeforeDisambiguation> entry : mapOntologiesBeforeDisambiguation.entrySet()) {
			OntologieBeforeDisambiguation value = entry.getValue();
			List<ConceptsInText> listConceptsInText = value.getListConcepts();
			while (principal.existeTermeAmbigueInOntologie(listConceptsInText)) {
				System.out.println("	* Desambiguisation niveau phrase : ");
				for (int i = 0; i < listConceptsInText.size(); i++) {
					value.displayConcept(i);
					value.filter(i, LevelSearch.PHRASE, typeMethodDesambiguisation);
				}
				System.out.println("	* Desambiguisation niveau paragraphe : ");
				for (int i = 0; i < listConceptsInText.size(); i++) {
					value.displayConcept(i);
					value.filter(i, LevelSearch.PARAGRAPHE, typeMethodDesambiguisation);
				}
				System.out.println("	* Desambiguisation niveau Document : ");
				for (int i = 0; i < listConceptsInText.size(); i++) {
					value.displayConcept(i);
					value.filter(i, LevelSearch.DOCUMENT, typeMethodDesambiguisation);
				}
				System.out.println("	* Desambiguisation entre distance ambigue : ");
				List<ConceptsInText> listConceptsAmbigue = principal.getListTermesAmbigue(listConceptsInText);
				principal.desambiguisterListeTermesAmbigue(listConceptsAmbigue);
			}
		}
		
		//créer la liste OntologieAfterDisambiguation
		for (Entry<String, OntologieBeforeDisambiguation> entry : mapOntologiesBeforeDisambiguation.entrySet()) {
			OntologieBeforeDisambiguation ontologieBeforeDisambiguation = entry.getValue();
			OntologieAfterDisambiguation ontologieAfterDisambiguation = new OntologieAfterDisambiguation();
			ontologieAfterDisambiguation.setName(ontologieBeforeDisambiguation.getName());
			List<ConceptsInText> listConceptsInTextOntologieBeforeDisambiguation = ontologieBeforeDisambiguation.getListConcepts();
			List<ConceptInText> listConceptAfterDisambiguation = new ArrayList<ConceptInText>();
			for (int i = 0; i < listConceptsInTextOntologieBeforeDisambiguation.size(); i++) {
				ConceptsInText conceptsBeforeDisambiguation = listConceptsInTextOntologieBeforeDisambiguation.get(i);
				ConceptJwnl conceptBeforeDisambiguation = conceptsBeforeDisambiguation.getListConceptJwnl().get(0);//après la disambiguisation, cette liste doit contenir un seul element 
				ConceptInText conceptAfterDisambiguation = new ConceptInText();
				conceptAfterDisambiguation.setConceptJwnl(conceptBeforeDisambiguation);
				conceptAfterDisambiguation.setPositionInText(conceptsBeforeDisambiguation.getPositionInText());
				listConceptAfterDisambiguation.add(conceptAfterDisambiguation);				
			}
			ontologieAfterDisambiguation.setListConcepts(listConceptAfterDisambiguation);
			listOntologiesAfterDisambiguation.add(ontologieAfterDisambiguation);
		}
		System.out.println("2. Liste des concepts après désambiguisation : ");
		
		//calculer le poids de chaque ontologie dans le document
		int[] poidsOntologies = new int[listOntologiesAfterDisambiguation.size()];
		int nbParagraphe = listParagrphes.size();
	    for (int i = 0; i < listOntologiesAfterDisambiguation.size(); i++) {
	    	OntologieAfterDisambiguation ontologieAfterDisambiguation = listOntologiesAfterDisambiguation.get(i);
	    	for (int j = 0; j < ontologieAfterDisambiguation.getListConcepts().size(); j++) {
		    	ontologieAfterDisambiguation.displayConcept(j);	    		
	    	}
	    	int poids = ontologieAfterDisambiguation.getPoidsOntologie(nbParagraphe, typeMethodCalculScore);
	    	poidsOntologies[i] = poids;
	    }
		
		//les chemins entre les synsets
	    for (int i = 0; i < listOntologiesAfterDisambiguation.size(); i++) {
	    	OntologieAfterDisambiguation ontologieAfterDisambiguation = listOntologiesAfterDisambiguation.get(i);
	    	Set<String> setConcepts1 = new HashSet<String>();
	    	for (int j = 0; j < ontologieAfterDisambiguation.getListConcepts().size() - 1; j++) {
		    	Set<String> setConcepts2 = new HashSet<String>();
	    		ConceptInText c1 = ontologieAfterDisambiguation.getListConcepts().get(j);
	    		String n1 = c1.getConceptJwnl().getNumber();
	    		boolean exist1 = setConcepts1.contains(n1);
	    		if (!exist1) {
	    			setConcepts1.add(n1);
			    	for (int k = j + 1; k < ontologieAfterDisambiguation.getListConcepts().size(); k++) {
			    		ConceptInText c2 = ontologieAfterDisambiguation.getListConcepts().get(k);
			    		String n2 = c2.getConceptJwnl().getNumber();
			    		boolean exist2 = setConcepts2.contains(n2);
			    		boolean exist3 = setConcepts1.contains(n2);
			    		if (!exist2 && !exist3 && !n1.equals(n2)) {
			    			setConcepts2.add(n2);
				    		WordnetHelp.printChemin(c1.getConceptJwnl(), c2.getConceptJwnl());		    			
			    		}
			    	}	    			
	    		}
	    	}
	    }				
	}
}
