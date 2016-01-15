package com.document;

import com.tagger.*;

import java.util.ArrayList;
import java.util.List;

import rita.wordnet.jwnl.JWNLException;
import rita.wordnet.jwnl.wndata.POS;

import com.jwnl.WordnetHelp;
import com.ontologies.ConceptJwnl;
import com.properties.GeneralConstants;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Phrase {
	private String value;
	private int positonInParagraph; //numéro de la phrase dans le paragraphe
	private int positionOfParagrapheWhichContainsWordInDocument;//numéro du paragraphe auquel la phrase est affecté
	private List<Mot> listMots;//list des mots de cette phrase
		
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getPositonInParagraph() {
		return positonInParagraph;
	}
	public void setPositonInParagraph(int paragraphePosition) {
		this.positonInParagraph = paragraphePosition;
	}
	public int getPositionOfParagrapheWhichContainsWordInDocument() {
		return positionOfParagrapheWhichContainsWordInDocument;
	}
	public void setPositionOfParagrapheWhichContainsWordInDocument(int paragrapheAffect) {
		this.positionOfParagrapheWhichContainsWordInDocument = paragrapheAffect;
	}
	public List<Mot> getListMots() {
		return listMots;
	}
	public void setListMots(List<Mot> listMots) {
		this.listMots = listMots;
	}
	public void loadListMots() throws JWNLException {
		listMots = new ArrayList<Mot>();
		TagText tagText = TagText.getInstance();
		MaxentTagger tagger = tagText.getMaxentTagger();
		String phraseWithType = tagger.tagString(value); //exemple : value = "Paul is taller than John."; phraseWithType = "Paul/NNP is/VBZ taller/JJR than/IN John/NNP ./."
		String[] motsWithType = phraseWithType.split(GeneralConstants.SEPARATEUR_ESPACE);
		
		for (int i = 0; i < motsWithType.length; i++) {
			String[] motWithType = motsWithType[i].split(GeneralConstants.SEPARATEUR_SLASH);
			if (motWithType.length > 1) {
				POS pos = tagText.getTypeOfWord(motWithType[1]);
				String base = null;
				if ( pos != null) {
					base = WordnetHelp.getBase(pos, motWithType[0]);				
				}
				Mot mot = new Mot();
				mot.setValue(motWithType[0]);
				mot.setBase(base);
				mot.setType(pos);
				PositionsInText positionInText = new PositionsInText();
				positionInText.setPositionOfParagrapheWhichContainsWordInDocument(positionOfParagrapheWhichContainsWordInDocument);
				positionInText.setPositionOfPhraseWhichContainsWordInParagrphe(positonInParagraph);
				positionInText.setPositionInPhrase(i + 1);
				mot.setPositionInText(positionInText);
				listMots.add(mot);								
			}
		}
	}
	
	public boolean isConceptExistInPhrase(ConceptJwnl concept, Mot mot) {
		concept.setType();//affecter le bon type du concept
		String terme = concept.getTerme();
		String[] listMotsForConcept = terme.split(GeneralConstants.SEPARATEUR_UNDERSCORE);
		boolean isConceptExistInPhrase = true;
		int lenghtPhraseFromPositionMot = listMots.size() + 1 - mot.getPositionInText().getPositionInPhrase();
		if ( lenghtPhraseFromPositionMot < concept.getLength()) {
			isConceptExistInPhrase = false;//car la phrase n'a pas assez de mot pour etre egal au terme du concept
		} else if ( concept.getLength() == 1 && concept.getType().equals(POS.NOUN) && !mot.getType().equals(POS.NOUN)) {
			isConceptExistInPhrase = false;//si le concept est un nom de taille un et le mot n'est pas un nom, on ne prend pas le concept
		} else {
			for (int i = 0; i < concept.getLength(); i++) {
				Mot motInPhrase = listMots.get(mot.getPositionInText().getPositionInPhrase() - 1 + i);
				String motInConcept = listMotsForConcept[i];
				if ( !motInConcept.equals(motInPhrase.getValue()) ) {//si le mot du concept est different du mot de la phrase de sa base (base du mot eats, est eat), on sort de la boucle et renvoie false
					if ( !motInConcept.equals(motInPhrase.getBase()) ) {
						isConceptExistInPhrase = false;
						break;
					}
				}
			}
		}
		return isConceptExistInPhrase;
	}
	//renvoyer le mot dans la phrase si il existe, à partir d'une position
	public Mot getMotInPhrase(String motDuConcept , int positionOfSearch) {
		List<Mot> listMots = getListMots();
		for (int k = positionOfSearch - 1; k < listMots.size(); k++ ) {
			Mot motAcomparer = listMots.get(k);
			String base = motAcomparer.getBase();
			if ( base != null) {//si la base du mot != null, comparer la base, sinon comparer le value
				if (base.equals(motDuConcept)) {
					return motAcomparer;
				}
			} else {
				String value = motAcomparer.getValue();
				if ( value.equals(motDuConcept)) {
					return motAcomparer;
				}
			}
		}
		return null;
	}

	
}
