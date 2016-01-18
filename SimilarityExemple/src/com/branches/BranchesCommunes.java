package com.branches;

import java.util.ArrayList;
import java.util.List;

import com.similarity.SynsetSimilarity;

public class BranchesCommunes {

	/**
	 * CalculScore
	 */
	private static BranchesCommunes branchesCommunes;

	/**
	 * Construncteur
	 */
	public BranchesCommunes() {
		super();
	}

	/**
	 * Returns the fichierGeometrieDAO instance (Design Pattern : Singleton)
	 * 
	 * @return fichierGeometrieDAO
	 */
	public static synchronized BranchesCommunes getInstance() {
		if (branchesCommunes == null)
			branchesCommunes = new BranchesCommunes();
		return branchesCommunes;
	}	
	
	///chercher le synset qui a le plus de numéro de branches pour T1
	public int maxBrancheSyn(List<SynsetSimilarity> Ti) {
		int maxBr = 0;
		for (int i = 0; i < Ti.size(); i++) {
			SynsetSimilarity T = Ti.get(i);
			int nbBr = T.getListNumeroBranche().size();
			if (nbBr > maxBr) {
				maxBr = nbBr;
			}
		}
		return maxBr;
		
	}
	
	//Trier T1 par ordre croissant des nombres de branches de ses synsets
	public List<SynsetSimilarity> Tri(List<SynsetSimilarity> Ti) throws CloneNotSupportedException {
		List<SynsetSimilarity> ListTrie = new ArrayList<SynsetSimilarity>();//nouvelle liste trie
		int ch = 1;//je place d’abord les synset ayant nombre branche= 1 puis 2 puis 3…..
		int maxbr = maxBrancheSyn(Ti);
		do {
			for (int i = 0; i < Ti.size(); i++) {
				SynsetSimilarity T = Ti.get(i);
				int nbBr = T.getListNumeroBranche().size();
				if (nbBr == ch) {				
					SynsetSimilarity TTrie = (SynsetSimilarity) T.clone();
					ListTrie.add(TTrie);
				}
			}
			ch++;
	   } while (ch <= maxbr);
		return ListTrie;		
	}
	
	//creation de Tfustrie  (T1trie+T2trie sans duplication)
	public List<SynsetSimilarity> branchefusion(List<SynsetSimilarity> T1trie, List<SynsetSimilarity> T2trie) throws CloneNotSupportedException {
		List<SynsetSimilarity> Tfustrie = new ArrayList<SynsetSimilarity>();//nouvelle liste trie
		int i = 0;
		int j = 0;
		int N4T1 = T1trie.size();
		int N4T2 = T2trie.size();
		int cpt = 0;
		int l = 0;
		int k = 0;
		boolean dup = false;
		while ( i < N4T1 ) {
			SynsetSimilarity Ti = T1trie.get(i);
			int nb1 = Ti.getListNumeroBranche().size();
			while ( j < N4T2 ) {
				SynsetSimilarity Tj = T2trie.get(j);
				int nb2 = Tj.getListNumeroBranche().size();
				if ( nb1 < nb2 ) {
					dup = false;
					cpt = 0;
					l = 0;
					k = Tfustrie.size();
					while ( (l < k) && (!dup) ) {
						SynsetSimilarity Tl = Tfustrie.get(l);
						int nb3 = Tl.getListNumeroBranche().size();	
						if ( nb1 == nb3 ) {
							for (int x = 0; x < nb1; x++) {
								int y = 0;
								boolean trouv = false;
								while ( (y < nb3) && (!trouv) ) {
									String brx = Ti.getListNumeroBranche().get(x);
									String bry = Tl.getListNumeroBranche().get(y);
									if (brx.equals(bry)) {
										cpt++;
										trouv = true;
									} else {
										y++;
									}
								}
							}
							if (cpt == nb1) {
								dup = true;
							}
							l++;
						}
					}
					if ( !dup ) {
						SynsetSimilarity tClone = (SynsetSimilarity) Ti.clone();
						Tfustrie.add(tClone);	
					}
					i++;
				} else if (nb1 > nb2) {
					dup = false;
					cpt = 0;
					l = 0;
					k = Tfustrie.size();
					while ( (l < k) && (!dup) ) {
						SynsetSimilarity Tl = Tfustrie.get(l);
						int nb3 = Tl.getListNumeroBranche().size();	
						if ( nb2 == nb3 ) {
							for (int x = 0; x < nb2; x++) {
								int y = 0;
								boolean trouv = false;
								while ( (y < nb3) && (!trouv) ) {
									String brx = Tj.getListNumeroBranche().get(x);
									String bry = Tl.getListNumeroBranche().get(y);
									if (brx.equals(bry)) {
										cpt++;
										trouv = true;
									} else {
										y++;
									}
								}
							}
							if (cpt == nb2) {
								dup = true;
							}
							l++;
						}
					}
					if ( !dup ) {
						SynsetSimilarity tClone = (SynsetSimilarity) Tj.clone();
						Tfustrie.add(tClone);	
					}
					j++;					
				} else { //nb1==nb2
					dup = false;
					cpt = 0;
					l = 0;
					k = Tfustrie.size();
					while ( (l < k) && (!dup) ) {
						SynsetSimilarity Tl = Tfustrie.get(l);
						int nb3 = Tl.getListNumeroBranche().size();	
						if ( nb1 == nb3 ) {
							for (int x = 0; x < nb1; x++) {
								int y = 0;
								boolean trouv = false;
								while ( (y < nb3) && (!trouv) ) {
									String brx = Ti.getListNumeroBranche().get(x);
									String bry = Tl.getListNumeroBranche().get(y);
									if (brx.equals(bry)) {
										cpt++;
										trouv = true;
									} else {
										y++;
									}
								}
							}
							if (cpt == nb1) {
								dup = true;
							}
							l++;
						}
					}
					if ( !dup ) {
						SynsetSimilarity tClone = (SynsetSimilarity) Ti.clone();
						Tfustrie.add(tClone);	
					}
					i++;
					
					dup = false;
					cpt = 0;
					l = 0;
					k = Tfustrie.size();
					while ( (l < k) && (!dup) ) {
						SynsetSimilarity Tl = Tfustrie.get(l);
						int nb3 = Tl.getListNumeroBranche().size();	
						if ( nb2 == nb3 ) {
							for (int x = 0; x < nb2; x++) {
								int y = 0;
								boolean trouv = false;
								while ( (y < nb3) && (!trouv) ) {
									String brx = Tj.getListNumeroBranche().get(x);
									String bry = Tl.getListNumeroBranche().get(y);
									if (brx.equals(bry)) {
										cpt++;
										trouv = true;
									} else {
										y++;
									}
								}
							}
							if (cpt == nb2) {
								dup = true;
							}
							l++;
						}
					}
					if ( !dup ) {
						SynsetSimilarity tClone = (SynsetSimilarity) Tj.clone();
						Tfustrie.add(tClone);	
					}
					j++;					
				}
			}			
		}
		
		while ( i < N4T1 ) {
			SynsetSimilarity Ti = T1trie.get(i);
			int nb1 = Ti.getListNumeroBranche().size();
			dup = false;
			cpt = 0;
			l = 0;
			k = Tfustrie.size();
			while ( (l < k) && (!dup) ) {
				SynsetSimilarity Tl = Tfustrie.get(l);
				int nb3 = Tl.getListNumeroBranche().size();	
				if ( nb1 == nb3 ) {
					for (int x = 0; x < nb1; x++) {
						int y = 0;
						boolean trouv = false;
						while ( (y < nb3) && (!trouv) ) {
							String brx = Ti.getListNumeroBranche().get(x);
							String bry = Tl.getListNumeroBranche().get(y);
							if (brx.equals(bry)) {
								cpt++;
								trouv = true;
							} else {
								y++;
							}
						}
					}
					if (cpt == nb1) {
						dup = true;
					}
					l++;
				}
			}
			if ( !dup ) {
				SynsetSimilarity tClone = (SynsetSimilarity) Ti.clone();
				Tfustrie.add(tClone);	
			}
			i++;			
		}
		while ( j < N4T2 ) {
			SynsetSimilarity Tj = T2trie.get(j);
			int nb2 = Tj.getListNumeroBranche().size();
			dup = false;
			cpt = 0;
			l = 0;
			k = Tfustrie.size();
			while ( (l < k) && (!dup) ) {
				SynsetSimilarity Tl = Tfustrie.get(l);
				int nb3 = Tl.getListNumeroBranche().size();	
				if ( nb2 == nb3 ) {
					for (int x = 0; x < nb2; x++) {
						int y = 0;
						boolean trouv = false;
						while ( (y < nb3) && (!trouv) ) {
							String brx = Tj.getListNumeroBranche().get(x);
							String bry = Tl.getListNumeroBranche().get(y);
							if (brx.equals(bry)) {
								cpt++;
								trouv = true;
							} else {
								y++;
							}
						}
					}
					if (cpt == nb2) {
						dup = true;
					}
					l++;
				}
			}
			if ( !dup ) {
				SynsetSimilarity tClone = (SynsetSimilarity) Tj.clone();
				Tfustrie.add(tClone);	
			}
			j++;			
		}
		return Tfustrie;
	}
	
	//déterminer les branches différentes de T1 et T2 creation du vecteur branchedif
	public List<SynsetSimilarity> differente(List<SynsetSimilarity> Tfustrie) throws CloneNotSupportedException {
		List<SynsetSimilarity> Branchedif = new ArrayList<SynsetSimilarity>();//nouvelle liste trie
		int NTfustrie = Tfustrie.size();
		int nb1;
		boolean dup;
		int l;
		int nb2;
		int k;
		int cpt;
		for (int i = 0; i < NTfustrie; i++) {
			SynsetSimilarity Ti = Tfustrie.get(i);
			nb1 = Ti.getListNumeroBranche().size();
			dup = false;
			l = 0;
			k = Branchedif.size();
			while ( (l < k) && (!dup) ) {
				SynsetSimilarity Tl = Branchedif.get(l);
				nb2 = Tl.getListNumeroBranche().size(); 
				cpt = 0;
				for (int ch1 = 0; ch1 < nb1; ch1++) {
					for (int ch2 = 0; ch2 < nb2; ch2++) {
						String brx = Ti.getListNumeroBranche().get(ch1);
						String bry = Tl.getListNumeroBranche().get(ch2);		
						if (brx.equals(bry)) {
							cpt++;
						}
					}
				}
				if ( cpt == nb2 ) {
					dup = true;
				}
			}
			if (!dup) {
				SynsetSimilarity tClone = (SynsetSimilarity) Ti.clone();
				Branchedif.add(tClone);	
			}
		}
		return Branchedif;
	}
	
	
}
