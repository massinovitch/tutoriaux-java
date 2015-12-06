package com.arbre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.properties.GeneralConstants;

public class CreerArbreXML {
	private Map<String, Noeud> mapRacines;
	Map<String, Noeud> mapNoeuds;
	
	public CreerArbreXML() {
		mapRacines = new TreeMap<String, Noeud>();		
		mapNoeuds = new HashMap<String, Noeud>();
	}
	private void enregistre(String fichier, Document document) throws FileNotFoundException, IOException {
		// On utilise ici un affichage classique avec getPrettyFormat()
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		// Remarquez qu'il suffit simplement de créer une instance de
		// FileOutputStream
		// avec en argument le nom du fichier pour effectuer la
		// sérialisation.
		sortie.output(document, new FileOutputStream(fichier));
	}
	
	private Element getElement(String synset) {
		Noeud valeur = mapNoeuds.get(synset);
		Element r = new Element("noeud");
		Attribute attribute = new Attribute("synset", valeur.getSynset());
		r.setAttribute(attribute);
		attribute = new Attribute("branche", valeur.getBranche());
		r.setAttribute(attribute);
		attribute = new Attribute("niveau", Integer.toString(valeur.getNiveau()));
		r.setAttribute(attribute);
		for (String synsetFils : valeur.getListFils()) {
			setBrancheNiveau(valeur);
			Element fils = getElement(synsetFils);
			r.addContent(fils);
		}
		return r;		
	}
	
	private void setBrancheNiveau(Noeud noeud) {
		if ( noeud.getListFils().size() == 1) {
			String synsetFils = noeud.getListFils().get(0);
			Noeud noeudFils = mapNoeuds.get(synsetFils);
			noeudFils.setBranche(noeud.getBranche());
			noeudFils.setNiveau(noeud.getNiveau() + 1);
		} else {
			for (int i = 0; i < noeud.getListFils().size(); i++) {
				int indice = i + 1;
				String synsetFils = noeud.getListFils().get(i);
				Noeud noeudFils = mapNoeuds.get(synsetFils);
				noeudFils.setBranche(noeud.getBranche() + "." + indice);
				noeudFils.setNiveau(noeud.getNiveau() + 1);		
			}
		}
	}

	private void creerXml() throws FileNotFoundException, IOException {
		Element racine = new Element("arbre");
		Document document = new Document(racine);		
		for(Entry<String, Noeud> entry : mapRacines.entrySet()) {
			Noeud valeur = entry.getValue();
			Element r = new Element("noeud");
			racine.addContent(r);
			Attribute attribute = new Attribute("synset", valeur.getSynset());
			r.setAttribute(attribute);
			attribute = new Attribute("branche", valeur.getBranche());
			r.setAttribute(attribute);
			attribute = new Attribute("niveau", Integer.toString(valeur.getNiveau()));
			r.setAttribute(attribute);
			for (String synsetFils : valeur.getListFils()) {
				setBrancheNiveau(valeur);
				Element fils = getElement(synsetFils);
				r.addContent(fils);
			}
		}
		enregistre("arbre.xml", document);
	}	
	public static void main(String[] args) throws IOException {
		CreerArbreXML creerArbreXML = new CreerArbreXML();
		Map<String, Noeud> mapNoeuds = creerArbreXML.mapNoeuds;
		Map<String, Noeud> mapRacines = creerArbreXML.mapRacines;
		int numero_racine = 1;
		Path fichier = Paths.get(GeneralConstants.FILENAME_DATA_NOUN);
		Charset charset = StandardCharsets.UTF_8;
        BufferedReader reader = Files.newBufferedReader(fichier, charset); 
		String line = null;
		for (int i = 0; i < 29; i++) {//eliminier les 30 premiere ligne
			reader.readLine();
		}
		//synset_offset  lex_filenum  ss_type  w_cnt  word  lex_id  [word  lex_id...]  p_cnt  [ptr...]  [frames...]  |   gloss 
		//00001740 03 n 01 entity 0 010 ~ 00002056 n 0000 ~  | that 
		while ((line = reader.readLine()) != null) {
			Noeud noeud = new Noeud();
		    String[] elementsLigne = line.split(GeneralConstants.SEPARATEUR_ESPACE);
		    String synset = elementsLigne[0];
		    noeud.setSynset(synset);
		    if (line.contains(GeneralConstants.CARACTERE_AROBASE)) {
				noeud = mapNoeuds.get(synset);
		    	if ( noeud == null ) {
		    		noeud = new Noeud();
		    		noeud.setSynset(synset);
					mapNoeuds.put(synset, noeud);
		    	}
		    } else {//si la ligne ne contient pas d'arobase, c une racine
		    	String num_racine = Integer.toString(numero_racine);
		    	noeud.setBranche(num_racine);
		    	noeud.setNiveau(1);
		    	mapRacines.put(num_racine, noeud);
		    	numero_racine++;
		    }
	    	String[] fils = line.split(GeneralConstants.CARACTERE_TREMA);
	    	if (fils.length > 1) {//il a des fils
		    	for (int i = 1; i < fils.length; i++) {
					String filsi = fils[i].trim();
					String[] elementsFilsi = filsi.split(GeneralConstants.SEPARATEUR_ESPACE);
					String synsetFils = elementsFilsi[0].trim();
					String typeFils = elementsFilsi[1].trim();
					if ( typeFils.equals(GeneralConstants.SUFFIXE_NOUN)) {
						noeud.getListFils().add(synsetFils);
						//rajouter le fils à la map ou le mettre à jour
						Noeud noeudFils = mapNoeuds.get(synsetFils);
				    	if ( noeudFils == null ) {
				    		noeudFils = new Noeud();
							noeudFils.setSynset(synsetFils);
							mapNoeuds.put(synsetFils, noeudFils);
				    	}
					}
				}		    		
	    	}			  
		}	
		reader.close();	
			
		creerArbreXML.creerXml();
	}
}
