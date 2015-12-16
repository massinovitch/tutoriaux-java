package com.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.JDOMException;

import rita.wordnet.jwnl.JWNLException;

import com.arbre.LireArbreXML;
import com.calculScore.CalculScore;
import com.jwnl.WordnetHelp;
import com.properties.Commun;
import com.properties.Constants;
import com.similarity.Similarity;
import com.similarity.SynsetSimilarity;

public class Principal {

	public static void main(String[] args) throws IOException, JWNLException, CloneNotSupportedException, JDOMException {		
        WordnetHelp.initialize("resources/file_properties.xml");
        int T1init, T2init; //N, M, N2T1, N2T2, N3T1, N3T2, N4T1, N4T2;
		CalculScore calculScore = CalculScore.getInstance();
		Similarity similarity = Similarity.getInstance();
		String fileNameArbreXml = Constants.getInstance().getProperty("fileName.arbreXml");
		LireArbreXML lireArbreXML = new LireArbreXML(fileNameArbreXml);
		String fileNameDocument1 = Constants.getInstance().getProperty("fileName.document1");//le document qui va etre lu et traité par notre algo
		List<SynsetSimilarity> T1 = new ArrayList<SynsetSimilarity>();
		String ontologie = calculScore.getSynsets(fileNameDocument1, T1);
		T1init = T1.size();
		String fileNameDocument2 = Constants.getInstance().getProperty("fileName.document2");//le document qui va etre lu et traité par notre algo
		List<SynsetSimilarity> T2 = new ArrayList<SynsetSimilarity>();
		calculScore.getSynsets(fileNameDocument2, T2);
		T2init = T2.size();
		List<SynsetSimilarity> Tinit = new ArrayList<SynsetSimilarity>();
		System.out.println("creatVectInitT1T2 : contenu Tinit");	
		similarity.creatVectInitT1T2(T1, T2, Tinit);
		System.out.println("synLiasion : contenu T1");	
		similarity.synLiasion(T1);
		//N = T1.size();
		System.out.println("synLiasion : contenu T2");	
		similarity.synLiasion(T2);
		//M = T2.size();
		System.out.println("enrichD_ancetre : contenu T1");	
		similarity.enrichD_ancetre(T2, T1);//enrichissement de T1 en fonction de T2
		//N2T1 = T1.size();
		System.out.println("enrichD_ancetre : contenu T2");	
		similarity.enrichD_ancetre(T1, T2);//enrichissement de T2 en fonction de T1
		//N2T2 = T2.size();
		System.out.println("parComDi : contenu T1");	
		similarity.parComDi(T1);//Enrichissement de D1 en rajoutant les parents communs a ses synsets
		//N3T1 = T1.size();
		System.out.println("parComDi : contenu T2");	
		similarity.parComDi(T2);//Enrichissement de D2 en rajoutant les parents communs a ses synsets
		//N3T2 = T2.size();
		System.out.println("parComD");	
		similarity.parComD(T1, T2);;//Enrichissement de D2 en rajoutant les parents communs a ses synsets
		//N4T1 = T1.size();
		//N4T2 = T2.size();
		System.out.println("Poidinit T1");
		similarity.poidsInit(T1);
		System.out.println("Poidinit T2");
		similarity.poidsInit(T2);
		float pas = Commun.getFloat();//taper le nombre pas
		System.out.println("poidsSraj T1");
		similarity.poidsSraj(T1, Tinit, T1init, pas);
		System.out.println("poidsSraj T2");
		similarity.poidsSraj(T2, Tinit, T2init, pas);
		System.out.println("brancheSyn T1");
		lireArbreXML.brancheSyn(T1);
		System.out.println("brancheSyn T2");
		lireArbreXML.brancheSyn(T2);
		float f = similarity.similarite(T1, T2, ontologie);
		System.out.println("similarite : " + f);

	}

}
