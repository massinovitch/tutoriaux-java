package com.similarity;

import java.util.List;

public class Similarity {
	
	public void creatVectInitT1T2 (List<Synset> T1,List<Synset> T2, List<Synset> Tinit) throws CloneNotSupportedException  {

		int j = 0;
		for (int i = 0; i < T1.size(); i++) {
			Synset s = (Synset) T1.get(i).clone();
			Tinit.add(s);
			j++;			
		}
		for (int i = 0; i < T2.size(); i++) {
			Synset s = (Synset) T2.get(i).clone();
			boolean dup = false; // pour eviter la duplication dans Tinit
			
			Tinit.add(s);
			j++;			
		}		
/*	
		Pour I=1 jusqu’à Minit faire
	
		S =T2[I]     {éviter de dupliquer les synsets}
		Dup =faux
		L =1
		Tant que  (L<= J-1) et (dup = faux) faire
		Si  S = Tinit[L] ) alors
	
		Dup = vrai
		Sinon
		L =L+1
	
		Finsi
		Fintantque
	
		Si (dup = faux) alors
	
		Tinit [J].num=T2[I].num
		J=j+1
	
		Finsi
	
		Finpour
	
		NbTinit =J-1
	
		fin*/


	}
}