package com.document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException; //Package à importer afin d'utiliser l'objet File
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import rita.wordnet.jwnl.JWNLException;


public class FileDocument {
	
	/* construit à partir d'un document text, les paragraphes, leurs phrases, et le mots */
	public List<Paragraphe> getListParagraphes(String filePath)
			throws IOException, JWNLException {
		List<Paragraphe> listParagraphes = new ArrayList<Paragraphe>();
		String sCurrentLine;
		StringBuilder currentText = new StringBuilder();
		int cptParagraphe = 1;
		InputStream ips = new FileInputStream(filePath);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		boolean firstWhiteLine = true;
	

		while ((sCurrentLine = br.readLine()) != null) {
			sCurrentLine = sCurrentLine.trim();
			if (sCurrentLine.equals("")) {
				if (firstWhiteLine) {
					firstWhiteLine = false;
					Paragraphe currentParagraphe = new Paragraphe();
					currentParagraphe.setValue(currentText.toString().toLowerCase());//sauvegarder le paragraphe ne miniscule
					currentParagraphe.setPositionInDocument(cptParagraphe);
					currentParagraphe.loadListPhrases();
					listParagraphes.add(currentParagraphe);
					currentText = new StringBuilder();
					cptParagraphe++;
				}// si il y a d'autres lignes blanches, on les ignore
			} else {
				firstWhiteLine = true;//il suffit de la mettre à true une fois, mais bon, ce n'est pas une operation couteuse.
				currentText.append(sCurrentLine);
			}
		}
		String lastPhrase = currentText.toString();
		if ( !lastPhrase.trim().equals("") ) {
			Paragraphe currentParagraphe = new Paragraphe();
			currentParagraphe.setValue(currentText.toString().toLowerCase());//sauvegarder le paragraphe ne miniscule
			currentParagraphe.setPositionInDocument(cptParagraphe);
			currentParagraphe.loadListPhrases();
			listParagraphes.add(currentParagraphe);
		}
		br.close();
		return listParagraphes;
	}
	
	/* construit à partir d'un document text, les paragraphes, leurs phrases, et le mots */
	public List<String> getListOntologies(String filePath)
			throws IOException {
		List<String> listOntologies = new ArrayList<String>();
		String sCurrentLine;
		InputStream ips = new FileInputStream(filePath);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);	

		while ((sCurrentLine = br.readLine()) != null) {
			sCurrentLine = sCurrentLine.trim();
			if (!sCurrentLine.equals("")) {
				listOntologies.add(sCurrentLine);
			}
		}
		br.close();
		return listOntologies;
	}	
}
