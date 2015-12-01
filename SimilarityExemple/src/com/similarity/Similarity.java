package com.similarity;

import java.util.List;

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
	
	public void creatVectInitT1T2 (List<Synset> T1,List<Synset> T2, List<Synset> Tinit) throws CloneNotSupportedException  {
		System.out.println("I. creatVectInitT1T2 : contenu Tinit");		
		for (int i = 0; i < T1.size(); i++) {
			Synset s = (Synset) T1.get(i).clone();
			System.out.println("               synset : " + s.getNumero() + ", " + s.getNom());	 
			Tinit.add(s);
		}
		for (int i = 0; i < T2.size(); i++) {
			Synset s = (Synset) T2.get(i).clone();
			if (!Tinit.contains(s)) {// pour eviter la duplication dans Tinit
    			System.out.println("               synset : " + s.getNumero() + ", " + s.getNom());	 
				Tinit.add(s);
			}
		}		
	}
}