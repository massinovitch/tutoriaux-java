package com.jwnl;

import com.ontologies.ConceptJwnl;

import rita.RiWordNet;
import rita.wordnet.jwnl.JWNLException;
import rita.wordnet.jwnl.dictionary.Dictionary;
import rita.wordnet.jwnl.dictionary.MorphologicalProcessor;
import rita.wordnet.jwnl.wndata.IndexWord;
import rita.wordnet.jwnl.wndata.POS;
import rita.wordnet.jwnl.wndata.PointerType;
import rita.wordnet.jwnl.wndata.Synset;
import rita.wordnet.jwnl.wndata.list.PointerTargetNode;
import rita.wordnet.jwnl.wndata.list.PointerTargetNodeList;
import rita.wordnet.jwnl.wndata.relationship.Relationship;
import rita.wordnet.jwnl.wndata.relationship.RelationshipFinder;
import rita.wordnet.jwnl.wndata.relationship.RelationshipList;

public class WordnetHelp {

	// Dictionary object
	public static Dictionary wordnet;
	public static RiWordNet c;

	// Initialize the database!
	public static void initialize(String propsFile) {
		c = new RiWordNet("WordNet-2.0/");
		wordnet = Dictionary.getInstance();
	}

	// Get the IndexWord object for a String and POS
	public static String getBase(POS pos, String s) throws JWNLException {
		MorphologicalProcessor morph = wordnet.getMorphologicalProcessor();
		IndexWord baseWord = morph.lookupBaseForm(pos, s);
		if (baseWord != null) {
			return baseWord.getLemma();
		}
		return null;
	}
	
	// get distance between words that are the same POS
	public static void printChemin(ConceptJwnl w1, ConceptJwnl w2) throws JWNLException
	{
		long synsetW1 = Long.parseLong(w1.getNumber().split("-")[0]) ;
		long synset1[] = {synsetW1};
		IndexWord start = new IndexWord(w1.getTerme(), w1.getType(), synset1);//c.getDictionary().getIndexWord(POS.NOUN, "bankrupt");
		long synsetW2 = Long.parseLong(w2.getNumber().split("-")[0]) ;
		long synset2[] = {synsetW2};
		System.out.println("le chemin entre  " + synsetW1 + "("+ w1.getTerme() + ") et " + synsetW2 + "("+ w2.getTerme() + ")");	
		IndexWord end = new IndexWord(w2.getTerme(), w2.getType(), synset2);//c.getDictionary().getIndexWord(POS.NOUN, "bankrupt");
		if ((start == null) || (end == null))
			return ;

		Synset startSynset = start.getSense(1);
		Synset endSynset = end.getSense(1);
		RelationshipList list = RelationshipFinder.getInstance()
				.findRelationships(startSynset, endSynset, PointerType.HYPERNYM);
		if ( list.size() != 0 ) {
			Relationship getShallowest = list.getShallowest();
			PointerTargetNodeList lists = getShallowest.getNodeList();
			for (int i = 0 ; i < lists.size(); i++) {
				PointerTargetNode s = (PointerTargetNode) lists.get(i);
				Synset c = s.getSynset();
				System.out.println("            mot " + i + " : " + c);				
			}				
		}

	}	
	
	public static float getDistance(ConceptJwnl w1, ConceptJwnl w2) throws JWNLException {			
		long synsetW1 = Long.parseLong(w1.getNumber().split("-")[0]) ;
		long synset1[] = {synsetW1};
		IndexWord iw1 = new IndexWord(w1.getTerme(), w1.getType(), synset1);//c.getDictionary().getIndexWord(POS.NOUN, "bankrupt");
		long synsetW2 = Long.parseLong(w2.getNumber().split("-")[0]) ;
		long synset2[] = {synsetW2};
		IndexWord iw2 = new IndexWord(w2.getTerme(), w2.getType(), synset2);//c.getDictionary().getIndexWord(POS.NOUN, "bankrupt");
		return c.getWordDistance(iw1, iw2);
	}

}
