package com.wordnet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.document.FileDocument;
import com.document.Mot;
import com.document.Phrase;
import com.ontologies.ConceptJwnl;
import com.ontologies.ConceptsInText;
import com.ontologies.OntologieAfterDisambiguation;
import com.ontologies.OntologieBeforeDisambiguation;
import com.properties.GeneralConstants;

public class FileWordnet {
	List<String> listOntologies;//tableau des ontologies
	Map<String, String> mapDomaineNames;//map (cle, domaine) lu � partir du fichier wn-domains-3.2-20070223
	List<ConceptJwnl> listConceptNoun;//list concept contenu dans fichier index.noun
	
	public FileWordnet() throws IOException{
		FileWordnetDomaine fileWordnetDomaine = new FileWordnetDomaine();
		FileDocument fileDocument = new FileDocument();
		listOntologies = fileDocument.getListOntologies(GeneralConstants.FILENAME_ONTOLOGIES);
		mapDomaineNames = fileWordnetDomaine.getMapDomaineNames(GeneralConstants.FILENAME_DOMAINE, listOntologies);
		listConceptNoun = getListConceptFromIndexFile(GeneralConstants.FILENAME_INDEX_NOUN);
	}
	
	//cr�er Map des ontologies qui va contenire les concepts avant la desambiguisation. dans cette fonction, on va affecter juste le nom de l'ontologie
	public Map<String, OntologieBeforeDisambiguation> getMapOntologieBeforeDisambiguation() {
		Map<String, OntologieBeforeDisambiguation> mapOntologieBeforeDisambiguation = new HashMap<String, OntologieBeforeDisambiguation>();
		for (int i = 0; i < listOntologies.size(); i++) {
			OntologieBeforeDisambiguation ontologieBeforeDisambiguation = new OntologieBeforeDisambiguation();
			ontologieBeforeDisambiguation.setName(listOntologies.get(i));
			mapOntologieBeforeDisambiguation.put(ontologieBeforeDisambiguation.getName(), ontologieBeforeDisambiguation);
		}
		return mapOntologieBeforeDisambiguation;
	}

	//cr�er Map des ontologies qui va contenire les concepts apr�s la desambiguisation. dans cette fonction, on va affecter juste le nom de l'ontologie
	public Map<String, OntologieAfterDisambiguation> getMapOntologieAfterDisambiguation() {
		Map<String, OntologieAfterDisambiguation> mapOntologieAfterDisambiguation = new HashMap<String, OntologieAfterDisambiguation>();
		for (int i = 0; i < listOntologies.size(); i++) {
			OntologieAfterDisambiguation ontologieAfterDisambiguation = new OntologieAfterDisambiguation();
			ontologieAfterDisambiguation.setName(listOntologies.get(i));
			mapOntologieAfterDisambiguation.put(ontologieAfterDisambiguation.getName(), ontologieAfterDisambiguation);
		}
		return mapOntologieAfterDisambiguation;
	}
	
	//renvoyer � partir d'une liste de concept, les concept qui ont un terme commen�ant par mot, et egal � ce qui suit dans la phrase
	public ConceptsInText getListConceptBeginWithMotAndEqualWithTerme(Phrase phrase, Mot mot) throws CloneNotSupportedException {
		ConceptsInText listConceptBeginWithMotAndEqualWithTerme = new ConceptsInText();
		List<ConceptJwnl> listConceptJwnl = new ArrayList<ConceptJwnl>();
		List<ConceptJwnl> listConcept = new ArrayList<ConceptJwnl>();
		listConcept.addAll(listConceptNoun);
		Iterator<ConceptJwnl> i = listConcept.iterator();
		while(i.hasNext()){
			ConceptJwnl concept = i.next();
			boolean isConceptExistInPhrase = phrase.isConceptExistInPhrase(concept, mot);//voir si le concept existe � l'identique dans la phrase
			if (isConceptExistInPhrase) {
				ConceptJwnl conceptJwnl = (ConceptJwnl) concept.clone();//on clone l'objet pour ne pas modifier les liste de d�part
				conceptJwnl.setType();//on aura besoin du type du concept pour les distances
				listConceptJwnl.add(conceptJwnl);
			}					
			
		}
		listConceptBeginWithMotAndEqualWithTerme.setListConceptJwnl(listConceptJwnl);
		listConceptBeginWithMotAndEqualWithTerme.setPositionInText(mot.getPositionInText());
		
		return listConceptBeginWithMotAndEqualWithTerme;
	}
	
	/* construit � partir du fichier index.verb, index.noun; index.adj, index.adv une list (Concepts)tri�e par taille des termes*/
	public List<ConceptJwnl> getListConceptFromIndexFile(String filePath)
			throws IOException {
		List<ConceptJwnl> listConcepts = new ArrayList<ConceptJwnl>();
		String sCurrentLine;
		InputStream ips = new FileInputStream(filePath);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		for (int j = 1; j < 30; j++) {
			br.readLine();
		}
		while ((sCurrentLine = br.readLine()) != null) {
			sCurrentLine = sCurrentLine.trim();			
			String[] lineOfConcepts = sCurrentLine.split(GeneralConstants.SEPARATEUR_ESPACE); 
			String typeOfTerme = lineOfConcepts[1];//v : verb, r : adverb, a : adjectif
			int numberOfConcepts = Integer.valueOf(lineOfConcepts[2]) ;//identifiant du concept auquels appartient le terme
			for (int i = lineOfConcepts.length - 1; i >= lineOfConcepts.length - numberOfConcepts; i-- ) {
				String identifiantOfConcept = lineOfConcepts[i] + GeneralConstants.SEPARATEUR_TIRET + typeOfTerme;
				String nameOntologie = mapDomaineNames.get(identifiantOfConcept);
				if ( nameOntologie != null ) {//si != null, �a veut que ce concept appartient � une ontologie qui est pr�sentes dans les ontologies � tester
					ConceptJwnl currentConcept = new ConceptJwnl();
					currentConcept.setTerme(lineOfConcepts[0]);
					String[] mots = currentConcept.getTerme().split(GeneralConstants.SEPARATEUR_UNDERSCORE);
					currentConcept.setLength(mots.length);
					currentConcept.setNumber(identifiantOfConcept);	
					currentConcept.setNameOntologie(nameOntologie);
					listConcepts.add(currentConcept);					
				}
			}
		}
		br.close();
		Collections.sort(listConcepts);  
		return listConcepts;
	}		
	
	//retourner � partir de la liste listConceptsBeginWithMot, les termes qui ont la longueur et le pourcentage requis
	private int getBetterLength(ConceptsInText conceptsInText) {
		int betterLength = 0;
		List<ConceptJwnl> listConcepts = conceptsInText.getListConceptJwnl();
		for ( int i = 0; i < listConcepts.size(); i++) {//la list est tri� par la taille du length
			ConceptJwnl currentConcept = listConcepts.get(i);
			if (currentConcept.getLength() >= betterLength) {
				betterLength = currentConcept.getLength();
			}
			
		}
		return betterLength;
	}
	
	//renvoie la liste des concepts les plus long par ontologie.
	public Map<String, ConceptsInText> getTallerConcepts(ConceptsInText conceptsInText) {
		int betterLength = getBetterLength(conceptsInText);
		conceptsInText.setLengthConcept(betterLength);
		Map<String, ConceptsInText> mapTallerConceptsByOntologie = new HashMap<String, ConceptsInText>();
		List<ConceptJwnl> listConcepts = conceptsInText.getListConceptJwnl();
		for (int i = 0; i < listConcepts.size(); i++) {
			ConceptJwnl currentConcept = listConcepts.get(i);
			if ( currentConcept.getLength() == betterLength ) {
				String[] ontologieNames = currentConcept.getNameOntologie().split(GeneralConstants.SEPARATEUR_ESPACE);
				for (int j = 0; j < ontologieNames.length; j++) {
					ConceptsInText conceptsByOntologie = mapTallerConceptsByOntologie.get(ontologieNames[j]);
					if (conceptsByOntologie == null) {
						conceptsByOntologie = new ConceptsInText();
						conceptsByOntologie.setType(currentConcept.getType());
						conceptsByOntologie.setPositionInText(conceptsInText.getPositionInText());
						conceptsByOntologie.setLengthConcept(betterLength);					
						mapTallerConceptsByOntologie.put(ontologieNames[j], conceptsByOntologie);
					}
					conceptsByOntologie.getListConceptJwnl().add(currentConcept);
				}
			}
		}
		return mapTallerConceptsByOntologie;
	}	
}
