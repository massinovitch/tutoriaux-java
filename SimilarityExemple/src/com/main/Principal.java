package com.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rita.wordnet.jwnl.JWNLException;

import com.calculScore.CalculScore;
import com.jwnl.WordnetHelp;
import com.properties.Constants;
import com.similarity.Similarity;
import com.similarity.SynsetSimilarity;

public class Principal {

	public static void main(String[] args) throws IOException, JWNLException, CloneNotSupportedException {		
        WordnetHelp.initialize("resources/file_properties.xml");
		CalculScore calculScore = CalculScore.getInstance();
		Similarity similarity = Similarity.getInstance();
		String fileNameDocument1 = Constants.getInstance().getProperty("fileName.document1");//le document qui va etre lu et traité par notre algo
		List<SynsetSimilarity> T1 = calculScore.getSynsets(fileNameDocument1);
		String fileNameDocument2 = Constants.getInstance().getProperty("fileName.document2");//le document qui va etre lu et traité par notre algo
		List<SynsetSimilarity> T2 = calculScore.getSynsets(fileNameDocument2);
		List<SynsetSimilarity> Tinit = new ArrayList<SynsetSimilarity>();
		System.out.println("I. creatVectInitT1T2 : contenu Tinit");	
		similarity.creatVectInitT1T2(T1, T2, Tinit);
		System.out.println("I. synLiasion : contenu T1");	
		similarity.synLiasion(T1);
		System.out.println("I. synLiasion : contenu T2");	
		similarity.synLiasion(T2);
		System.out.println("I. enrichD_ancetre : contenu T1");	
		similarity.enrichD_ancetre(T2, T1);//enrichissement de T1 en fonction de T2
		System.out.println("I. enrichD_ancetre : contenu T2");	
		similarity.enrichD_ancetre(T1, T2);//enrichissement de T2 en fonction de T1
		System.out.println("I. parComDi : contenu T1");	
		similarity.parComDi(T1);//Enrichissement de D1 en rajoutant les parents communs a ses synsets
		System.out.println("I. parComDi : contenu T2");	
		similarity.parComDi(T2);//Enrichissement de D2 en rajoutant les parents communs a ses synsets
		System.out.println("I. parComD");	
		similarity.parComD(T1, T2);;//Enrichissement de D2 en rajoutant les parents communs a ses synsets
		System.out.println();

	}

}
