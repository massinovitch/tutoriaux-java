package com.ontologies;

import java.util.ArrayList;
import java.util.List;

import rita.wordnet.jwnl.JWNLException;
import rita.wordnet.jwnl.wndata.POS;

import com.document.PositionsInText;
import com.properties.LevelSearch;

public class OntologieBeforeDisambiguation {
	
	private String name;	
	private List<ConceptsInText> listConcepts;
	
	public OntologieBeforeDisambiguation() {
		listConcepts = new ArrayList<ConceptsInText>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ConceptsInText> getListConcepts() {
		return listConcepts;
	}

	public void setListConcepts(List<ConceptsInText> listConcepts) {
		this.listConcepts = listConcepts;
	}
	
	//chercher voisin gauche avec le niveau de recherche adéquat
	/**
	 * @param indexConceptsInText : index du concept
	 * @param indexVoisin : index à partir du quel on va commencer à chercher le voisin
	 * @param levelSearch : niveau de recherche (phrase, paragraphe, document)
	 * @param typeMethod : type de methode recherche du voisin gauche
	 */
	public ConceptsInText findVg(int indexConceptsInText, int indexVoisin, String levelSearch, int typeMethod) {
		ConceptsInText conceptsInText = listConcepts.get(indexConceptsInText);
		PositionsInText postionConceptsInText = conceptsInText.getPositionInText();
		POS typeConcept = conceptsInText.getType();
		ConceptsInText vg = null;
		int cptConceptsVoisins = indexVoisin;
		//si on dépasse le niveau levelSearch, et on trouve rien, on va sortir
		if (typeConcept.equals(POS.NOUN)) {
			if (typeMethod == 1) {
				while ( cptConceptsVoisins >= 0 ) {//tant qu'il y a un voisin gauche					
						ConceptsInText vgTmp = listConcepts.get(cptConceptsVoisins);
						POS typeVgTmp = vgTmp.getType();
						if ( typeVgTmp.equals(typeConcept)) {
							PositionsInText positionVgTmp = vgTmp.getPositionInText();
							boolean samePhrase = (postionConceptsInText.getPositionOfPhraseWhichContainsWordInParagrphe() == positionVgTmp.getPositionOfPhraseWhichContainsWordInParagrphe());			
							boolean sameParagraphe = (postionConceptsInText.getPositionOfParagrapheWhichContainsWordInDocument() == positionVgTmp.getPositionOfParagrapheWhichContainsWordInDocument());
							if ( levelSearch.equals(LevelSearch.PHRASE) ) {
								if ( samePhrase && sameParagraphe) {//si l'element suivant est de la meme phrase.
									vg = vgTmp;
								}				
							} else if ( levelSearch.equals(LevelSearch.PARAGRAPHE) ){
								if ( sameParagraphe ) {//si l'element suivant est du meme paragraphe.
									vg = vgTmp;
								}		
							} else {//recherche au niveau du docuemnt
								vg = vgTmp;
							}															
							break;
						} else {
							cptConceptsVoisins--;
						}
				}
			}			
		}
		return vg;
	}
	
	//chercher voisin droite avec le niveau de recherche adéquat
	public ConceptsInText findVd(int indexConceptsInText, int indexVoisin, String levelSearch, int typeMethod) {
		ConceptsInText conceptsInText = listConcepts.get(indexConceptsInText);
		PositionsInText postionConceptsInText = conceptsInText.getPositionInText();
		POS typeConcept = conceptsInText.getType();
		ConceptsInText vd = null;
		int cptConceptsVoisins = indexVoisin;
		//si on dépasse le niveau levelSearch, et on trouve rien, on va sortir
		if (typeConcept.equals(POS.NOUN)) {
			if (typeMethod == 1) {
				while ( cptConceptsVoisins < listConcepts.size() ) {//tant qu'il y a un voisin droite	
					ConceptsInText vdTmp = listConcepts.get(cptConceptsVoisins);
					POS typeVdTmp = vdTmp.getType();
					if ( typeVdTmp.equals(typeConcept)) {
						PositionsInText positionVdTmp = vdTmp.getPositionInText();
						boolean samePhrase = (postionConceptsInText.getPositionOfPhraseWhichContainsWordInParagrphe() == positionVdTmp.getPositionOfPhraseWhichContainsWordInParagrphe());			
						boolean sameParagraphe = (postionConceptsInText.getPositionOfParagrapheWhichContainsWordInDocument() == positionVdTmp.getPositionOfParagrapheWhichContainsWordInDocument());	
						if ( levelSearch.equals(LevelSearch.PHRASE) ) {
							if ( samePhrase && sameParagraphe) {//si l'element suivant est de la meme phrase.
								vd = vdTmp;
							}				
						} else if ( levelSearch.equals(LevelSearch.PARAGRAPHE) ){
							if ( sameParagraphe ) {//si l'element suivant est du meme paragraphe.
								vd = vdTmp;
							}		
						} else {//recherche au niveau du docuemnt
							vd = vdTmp;
						}
						break;
					} else {
						cptConceptsVoisins++;
					}
				}
			}
		}
		return vd;
	}
	
	//desambiguiser l'élément qui se trouve à la position indexConceptsInText ( les termes sont insérés par ordre de leurs apparition dans la phrase dans listConcepts
	public boolean filter(int indexConceptsInText, String levelSearch, int typeMethod) throws JWNLException {
		ConceptsInText coneceptsInText = listConcepts.get(indexConceptsInText);
		if ( coneceptsInText.isAmbigue() ) {
			POS typeConcept = coneceptsInText.getType();
			if ( typeConcept.equals(POS.NOUN) ) {
				boolean trouv = false;
				int indexVoising = indexConceptsInText;
				int indexVoisind = indexConceptsInText;
				while (!trouv) {
					indexVoising--;
					indexVoisind++;
					ConceptsInText vg = findVg(indexConceptsInText, indexVoising, levelSearch, typeMethod);
					ConceptsInText vd = findVd(indexConceptsInText, indexVoisind, levelSearch, typeMethod);
					ConceptJwnl conceptJwnlVd = null;
					ConceptJwnl conceptJwnlVg = null;
					if ( (vg != null) && (vd != null) ) {
						if ( (vg.isAmbigue()) && (vd.isAmbigue()) ) {
							boolean isVdFilter = filter(indexVoisind, levelSearch, typeMethod);
							if ( isVdFilter ) {
								//utiliser vd pour desambiguiser conceptsInText
								conceptJwnlVd = vd.getListConceptJwnl().get(0);//premier element ( puisque cet element est desambiguiser, il doit contenir un seul concept jwnl)
							} else {//sinon, conceptsInText reste ambigue
								return false;
							}
						} else if ( vg.isAmbigue() ) {//utiliser vd
							conceptJwnlVd = vd.getListConceptJwnl().get(0);//premier element ( puisque cet element est desambiguiser, il doit contenir un seul concept jwnl)
						} else if ( vd.isAmbigue() ) {//utiliser vg
							conceptJwnlVg = vg.getListConceptJwnl().get(0);//premier element ( puisque cet element est desambiguiser, il doit contenir un seul concept jwnl)
						} else { //(!vg.isAmbigue()) && (!vd.isAmbigue()) )//utiliser les deux					
							conceptJwnlVd = vd.getListConceptJwnl().get(0);//premier element ( puisque cet element est desambiguiser, il doit contenir un seul concept jwnl)
							conceptJwnlVg = vg.getListConceptJwnl().get(0);//premier element ( puisque cet element est desambiguiser, il doit contenir un seul concept jwnl)
						}
					} else if ( vg != null ) {
						if ( !vg.isAmbigue() ) {
							conceptJwnlVg = vg.getListConceptJwnl().get(0);//premier element ( puisque cet element est desambiguiser, il doit contenir un seul concept jwnl)
						} else {//on ne fait rien, le concept rest ambigue
							return false;
						}
					} else if ( vd != null ) {
						if ( !vd.isAmbigue() ) {
							conceptJwnlVd = vd.getListConceptJwnl().get(0);//premier element ( puisque cet element est desambiguiser, il doit contenir un seul concept jwnl)
						} else {
							boolean isVdFilter = filter(indexVoisind, levelSearch, typeMethod);
							if ( isVdFilter ) {
								//utiliser vd pour desambiguiser conceptsInText
								conceptJwnlVd = vd.getListConceptJwnl().get(0);//premier element ( puisque cet element est desambiguiser, il doit contenir un seul concept jwnl)
							} else {//sinon, conceptsInText reste ambigue
								return false;
							}
						}
					} else {//else , les deux voisins sont null, donc return false
						return false;
					}
					trouv = coneceptsInText.keepMinConcept(conceptJwnlVg, conceptJwnlVd);				
				}
			} else {
				coneceptsInText.selectFirstConcept();
			}
		} //sinon, l'element n'est pas ambigue
		return true;
	}
	
}
