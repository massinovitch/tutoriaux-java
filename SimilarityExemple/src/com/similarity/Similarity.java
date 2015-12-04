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
	
	//recherche de l’ancêtre d’un synset
	private boolean ancetre(SynsetSimilarity s, SynsetSimilarity anc, ParameterReference parRef) throws JWNLException {
		SynsetSimilarity p, x;
		boolean exist, ancetre = false;
		List<SynsetSimilarity> echemin = new ArrayList<SynsetSimilarity>();
		WordnetHelp.buildChemin(s, anc, echemin);
		if (echemin.size() > 0) {
			x = s; 
			exist = true;
			do {
				List<SynsetSimilarity> listFathter = WordnetHelp.getFather(x);
				if ( listFathter.size() == 0 ) {//pere n existe pas
					exist = false;
					ancetre = false;
				} else {
					listFathter.retainAll(echemin);  //listFathter should have only common elements between listFathter and echemin
					if ( listFathter != null && listFathter.size() > 0) {
						p = listFathter.get(0);//prendre premier pere qui se trouve dans echemin
						if ( !p.equals(anc) ) {
							x = p;
							parRef.setNbTour( parRef.getNbTour() + 1 );
						} else {
							ancetre = true;
							exist = false;
							parRef.setNbTour( parRef.getNbTour() + 1 );
						}
					} else {
						ancetre = false;
						exist = false;
					}
				}
			} while (exist);
		} 
		return ancetre;
	}
	
	//enrichissement de Tj en fonction de Ti
	public void enrichD_ancetre(List<SynsetSimilarity> Ti, List<SynsetSimilarity> Tj) throws JWNLException {
		int lengthTj = Tj.size();
		ParameterReference parRef = new ParameterReference();
		for (int j = 0; j < lengthTj; j++) {	 
			SynsetSimilarity x = Tj.get(j);
			for (int i = 0; i < Ti.size(); i++) {
				SynsetSimilarity y = Ti.get(i);
				if (!x.equals(y)) {					
					if (ancetre(x, y, parRef)) {
						if (!Tj.contains(y)) {
							System.out.println("               synset : " + y.getNumero() + ", " + y.getNom());	 
							Tj.add(y);
						}
					}
				}
			}
		}
	}
	
	//Enrichissement de Di en rajoutant les parents communs a ses synsets
	public void parComDi(List<SynsetSimilarity> Ti) throws JWNLException {
		int lengthTi = Ti.size();
		for (int i = 0; i < lengthTi - 1; i++) {
			SynsetSimilarity s1 = Ti.get(i);
			for (int j = i + 1; j < lengthTi; j++) {
				SynsetSimilarity s2 = Ti.get(j);
				SynsetSimilarity pc = WordnetHelp.getCommonParent(s1, s2);
				if (pc != null) {
					if (!Ti.contains(pc)) {
						System.out.println("               synset : " + pc.getNumero() + ", " + pc.getNom());	 
						Ti.add(pc);
					}
				}

			}
		}		
	}
	
	//Enrichissement de D1 et D2 en rajoutant les parents communs à D1 et D2
	public void parComD(List<SynsetSimilarity> Ti, List<SynsetSimilarity> Tj) throws JWNLException {
		int lengthTi = Ti.size();
		int lengthTj = Tj.size();
		for (int i = 0; i < lengthTi ; i++) {
			SynsetSimilarity s1 = Ti.get(i);
			for (int j = 0; j < lengthTj; j++) {
				SynsetSimilarity s2 = Tj.get(j);	
				SynsetSimilarity pc = WordnetHelp.getCommonParent(s1, s2);
				if (pc != null) {
					if (!Ti.contains(pc)) {
						System.out.println("               synset add T1 : " + pc.getNumero() + ", " + pc.getNom());	 
						Ti.add(pc);
					}
					if (!Tj.contains(pc)) {
						System.out.println("               synset T2 : " + pc.getNumero() + ", " + pc.getNom());	 
						Tj.add(pc);
					}
				}
			}
			
		}
	}
	
	//poids init
	public void poidsInit(List<SynsetSimilarity> Ti) {
		for (int i = 0; i < Ti.size(); i++) {
			SynsetSimilarity s = Ti.get(i);
			s.setP(1);
		}
	}
	
	//calcul de la distance entre un synset rajouté et le synset initial le plus bas
	private int distance(SynsetSimilarity sraj, List<SynsetSimilarity> Tinit) throws JWNLException {
		int max = 0;
		for (int i = 0; i < Tinit.size(); i++) {
			SynsetSimilarity s = Tinit.get(i);
			ParameterReference parRef = new ParameterReference();
			if (ancetre(s, sraj, parRef)) {
				if (parRef.getNbTour() > max) {
					max = parRef.getNbTour();
				}
			}
		}
		return max;		
	}
	
	//calcul du poids des synsets rajoutés
	public void poidsSraj(List<SynsetSimilarity> Ti, List<SynsetSimilarity> Tinit, int Tiinit, float pas) throws JWNLException {
		for (int i = Tiinit; i < Ti.size(); i++) {
			SynsetSimilarity s = Ti.get(i);
			int dist = distance(s, Tinit);
			float p = 1 - (dist * pas);
			s.setP(p);
		}		
	}
	
	//Calcul de la somme des poids : numerateur/denominateur
	public float similarite(List<SynsetSimilarity> Ti, List<SynsetSimilarity> Tj) {
		float sim = 0;
		int somN = 0, somD = 0;
		float pp, pg;
		for (int i = 0; i < Ti.size(); i++) {
			SynsetSimilarity si = Ti.get(i);
			boolean trouv = false;
			int j = 0;
			while ( (j < Tj.size()) && !trouv ) {
				SynsetSimilarity sj = Tj.get(j);
				if ( si.equals(sj) ) {
					if (si.getP() == sj.getP()) {
						somN += si.getP();
						somD += si.getP();
					} else if (si.getP() < sj.getP()) {
						pp = si.getP();
						pg = sj.getP();
						somN += pp;
						somD += pg;
					} else {
						pp = sj.getP();
						pg = si.getP();
						somN += pp;
						somD += pg;
					}
					trouv = true;
				} else {
					j++;
				}
				
			}
			if ( !trouv ) {
				somD += si.getP();
			}
		}
		for (int i = 0; i < Tj.size(); i++) {
			SynsetSimilarity si = Tj.get(i);
			boolean trouv = false;
			int j = 0;
			while ( (j < Ti.size()) && !trouv ) {
				SynsetSimilarity sj = Ti.get(j);
				if ( si.equals(sj) ) {
					trouv = true;
				} else {
					j++;
				}
			}
			somD += si.getP();//Tj[i]
		}
		sim =  (float) somN / somD;
		return sim;
	}

}