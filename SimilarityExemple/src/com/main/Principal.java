package com.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rita.wordnet.jwnl.JWNLException;

import com.calculScore.CalculScore;
import com.jwnl.WordnetHelp;
import com.properties.Constants;
import com.similarity.Similarity;
import com.similarity.Synset;

public class Principal {

	public static void main(String[] args) throws IOException, JWNLException, CloneNotSupportedException {		
        WordnetHelp.initialize("resources/file_properties.xml");
		CalculScore calculScore = CalculScore.getInstance();
		Similarity similarity = Similarity.getInstance();
		String fileNameDocument1 = Constants.getInstance().getProperty("fileName.document1");//le document qui va etre lu et traité par notre algo
		List<Synset> T1 = calculScore.getSynsets(fileNameDocument1);
		String fileNameDocument2 = Constants.getInstance().getProperty("fileName.document2");//le document qui va etre lu et traité par notre algo
		List<Synset> T2 = calculScore.getSynsets(fileNameDocument2);
		List<Synset> Tinit = new ArrayList<Synset>();
		similarity.creatVectInitT1T2(T1, T2, Tinit);
		System.out.println();

	}

}
