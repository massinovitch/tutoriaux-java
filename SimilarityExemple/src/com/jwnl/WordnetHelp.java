package com.jwnl;

import java.util.ArrayList;
import java.util.List;

import com.ontologies.ConceptJwnl;
import com.properties.GeneralConstants;
import com.similarity.SynsetSimilarity;

import rita.RiWordNet;
import rita.wordnet.jwnl.JWNLException;
import rita.wordnet.jwnl.dictionary.Dictionary;
import rita.wordnet.jwnl.dictionary.MorphologicalProcessor;
import rita.wordnet.jwnl.wndata.IndexWord;
import rita.wordnet.jwnl.wndata.POS;
import rita.wordnet.jwnl.wndata.PointerType;
import rita.wordnet.jwnl.wndata.PointerUtils;
import rita.wordnet.jwnl.wndata.Synset;
import rita.wordnet.jwnl.wndata.Word;
import rita.wordnet.jwnl.wndata.list.PointerTargetNode;
import rita.wordnet.jwnl.wndata.list.PointerTargetNodeList;
import rita.wordnet.jwnl.wndata.relationship.Relationship;
import rita.wordnet.jwnl.wndata.relationship.RelationshipFinder;
import rita.wordnet.jwnl.wndata.relationship.RelationshipList;

public class WordnetHelp {

	// Dictionary object
	public static Dictionary wordnet;
	public static RiWordNet riWordNet;

	// Initialize the database!
	public static void initialize(String propsFile) {
		riWordNet = new RiWordNet("WordNet-2.0/");
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
	public static void buildChemin(SynsetSimilarity w1, SynsetSimilarity w2, List<SynsetSimilarity> listChemin) throws JWNLException
	{
		long synsetW1 = Long.parseLong(w1.getNumero().split(GeneralConstants.SEPARATEUR_TIRET)[0]) ;
		long synset1[] = {synsetW1};
		IndexWord start = new IndexWord(w1.getNom(), POS.NOUN, synset1);//c.getDictionary().getIndexWord(POS.NOUN, "bankrupt");
		long synsetW2 = Long.parseLong(w2.getNumero().split(GeneralConstants.SEPARATEUR_TIRET)[0]) ;
		long synset2[] = {synsetW2};
		IndexWord end = new IndexWord(w2.getNom(), POS.NOUN, synset2);//c.getDictionary().getIndexWord(POS.NOUN, "bankrupt");
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
				Synset r = s.getSynset();
				Word w = r.getWords()[0];
				POS p = r.getPOS();
				if (p.equals(POS.NOUN)) {
					SynsetSimilarity synsetSimilarity = new SynsetSimilarity();
					Long l = (Long) r.getKey();
					String key = Long.toString(l);
					int length = key.length();
					for (int j = length; j < 8; j++) {// le numero d un synset a la taille de 8 chiffres. on complete à droite par des zero si il n y a pas 8 chiffre
						key = "0" + key;
					}
					String synset = key + GeneralConstants.SEPARATEUR_TIRET + GeneralConstants.SUFFIXE_NOUN;
					synsetSimilarity.setNumero(synset);
					synsetSimilarity.setNom(w.getLemma());
					if ( !synsetSimilarity.equals(w1)) {//dans la liste des chemins retournée. on integre w2, mais pas w1
						listChemin.add(synsetSimilarity);						
					}
				}
			}				
		}
	}	
	
	public static List<SynsetSimilarity> getFather(SynsetSimilarity sense) throws JWNLException, NullPointerException {
		long synsetW1 = Long.parseLong(sense.getNumero().split(GeneralConstants.SEPARATEUR_TIRET)[0]) ;
		long synset1[] = {synsetW1};
		IndexWord indexWord = new IndexWord(sense.getNom(), POS.NOUN, synset1);//c.getDictionary().getIndexWord(POS.NOUN, "bankrupt");
		Synset synset = indexWord.getSense(1);
		PointerTargetNodeList lists = PointerUtils.getInstance().getDirectHypernyms(synset);
        // Iterate through the related list and make an ArrayList of Synsets to send back
        List<SynsetSimilarity> listFather = new ArrayList<SynsetSimilarity>();
		for (int i = 0 ; i < lists.size(); i++) {
			PointerTargetNode s = (PointerTargetNode) lists.get(i);
			Synset r = s.getSynset();
			Word w = r.getWords()[0];
			POS p = r.getPOS();
			if (p.equals(POS.NOUN)) {
				SynsetSimilarity synsetSimilarity = new SynsetSimilarity();
				Long l = (Long) r.getKey();
				String key = Long.toString(l);
				int length = key.length();
				for (int j = length; j < 8; j++) {// le numero d un synset a la taille de 8 chiffres. on complete à droite par des zero si il n y a pas 8 chiffre
					key = "0" + key;
				}
				String cle = key + GeneralConstants.SEPARATEUR_TIRET + GeneralConstants.SUFFIXE_NOUN;
				synsetSimilarity.setNumero(cle);
				synsetSimilarity.setNom(w.getLemma());
				listFather.add(synsetSimilarity);						
			}
		}				
        return listFather;
    }
	
	public static SynsetSimilarity getCommonParent(SynsetSimilarity w1, SynsetSimilarity w2) throws JWNLException {
		long synsetW1 = Long.parseLong(w1.getNumero().split(GeneralConstants.SEPARATEUR_TIRET)[0]) ;
		long synset1[] = {synsetW1};
		IndexWord start = new IndexWord(w1.getNom(), POS.NOUN, synset1);//c.getDictionary().getIndexWord(POS.NOUN, "bankrupt");
		long synsetW2 = Long.parseLong(w2.getNumero().split(GeneralConstants.SEPARATEUR_TIRET)[0]) ;
		long synset2[] = {synsetW2};
		IndexWord end = new IndexWord(w2.getNom(), POS.NOUN, synset2);//c.getDictionary().getIndexWord(POS.NOUN, "bankrupt");
		if ((start != null) && (end != null)) {
			Synset s = riWordNet.getCommonParent(start, end);
			if (s != null ) {
				Word w = s.getWords()[0];
				POS p = s.getPOS();
				if (p.equals(POS.NOUN)) {
					SynsetSimilarity synsetSimilarity = new SynsetSimilarity();
					Long l = (Long) s.getKey();
					String key = Long.toString(l);
					int length = key.length();
					for (int j = length; j < 8; j++) {// le numero d un synset a la taille de 8 chiffres. on complete à droite par des zero si il n y a pas 8 chiffre
						key = "0" + key;
					}
					String cle = key + GeneralConstants.SEPARATEUR_TIRET + GeneralConstants.SUFFIXE_NOUN;
					synsetSimilarity.setNumero(cle);
					synsetSimilarity.setNom(w.getLemma());
					return synsetSimilarity;
				}				
			}			
		}
		return null;
	}
	
	public static float getDistance(ConceptJwnl w1, ConceptJwnl w2) throws JWNLException {			
		long synsetW1 = Long.parseLong(w1.getNumber().split("-")[0]) ;
		long synset1[] = {synsetW1};
		IndexWord iw1 = new IndexWord(w1.getTerme(), w1.getType(), synset1);//c.getDictionary().getIndexWord(POS.NOUN, "bankrupt");
		long synsetW2 = Long.parseLong(w2.getNumber().split("-")[0]) ;
		long synset2[] = {synsetW2};
		IndexWord iw2 = new IndexWord(w2.getTerme(), w2.getType(), synset2);//c.getDictionary().getIndexWord(POS.NOUN, "bankrupt");
		return riWordNet.getWordDistance(iw1, iw2);
	}

}
