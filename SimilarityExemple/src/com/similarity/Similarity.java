package com.similarity;

import java.util.ArrayList;
import java.util.List;

import rita.wordnet.jwnl.JWNLException;

import com.jwnl.WordnetHelp;


public class Similarity {
	
	/**
	 * CalculScore
	 */
	private static Similarity similarity;

	/**
	 * Construncteur
	 */
	public Similarity() {
		super();
	}

	/**
	 * Returns the fichierGeometrieDAO instance (Design Pattern : Singleton)
	 * 
	 * @return fichierGeometrieDAO
	 */
	public static synchronized Similarity getInstance() {
		if (similarity == null)
			similarity = new Similarity();
		return similarity;
	}	
	
	//creation de Tinit = fusion T1 et T2 sans duplication
	public void creatVectInitT1T2(List<SynsetSimilarity> T1,List<SynsetSimilarity> T2, List<SynsetSimilarity> Tinit) throws CloneNotSupportedException  {
		for (int i = 0; i < T1.size(); i++) {
			SynsetSimilarity s = (SynsetSimilarity) T1.get(i).clone();
			System.out.println("               synset : " + s.getNumero() + ", " + s.getNom());	 
			Tinit.add(s);
		}
		for (int i = 0; i < T2.size(); i++) {
			SynsetSimilarity s = (SynsetSimilarity) T2.get(i).clone();
			if (!Tinit.contains(s)) {// pour eviter la duplication dans Tinit
    			System.out.println("               synset : " + s.getNumero() + ", " + s.getNom());	 
				Tinit.add(s);
			}
		}		
	}
	
	//-	rajouter à Ti les N synset D1 (chemin entre les synset de Ti)   -> Ti   (1…Tiinit…..N)
	public void synLiasion(List<SynsetSimilarity> T) throws JWNLException {	
		int T1init = T.size();
		for (int i = 0; i < T1init - 1; i++) {
			SynsetSimilarity s1 = T.get(i);
			for (int j = i + 1; j < T1init; j++) {
				SynsetSimilarity s2 = T.get(j);
				List<SynsetSimilarity> listChemin = new ArrayList<SynsetSimilarity>();
				WordnetHelp.buildChemin(s1, s2, listChemin);
				for (SynsetSimilarity s : listChemin) {
					if (!T.contains(s)) {
						T.add(s);
						System.out.println("               synset : " + s.getNumero() + ", " + s.getNom());	 
					}
				}
			}
		}
		
	}
}