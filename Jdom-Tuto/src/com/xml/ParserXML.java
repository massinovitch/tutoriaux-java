package com.xml;

import java.io.File;
import java.util.List;
import java.util.Iterator;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ParserXML {
	static Document document;
	static Element racine;

	static void afficheALL() {
		// On crée une List contenant tous les noeuds "etudiant" de l'Element
		// racine
		List listEtudiants = racine.getChildren("etudiant");
		// On crée un Iterator sur notre liste
		Iterator i = listEtudiants.iterator();
		while (i.hasNext()) {
			// On recrée l'Element courant à chaque tour de boucle afin de
			// pouvoir utiliser les méthodes propres aux Element comme :
			// sélectionner un noeud fils, modifier du texte, etc...
			Element courant = (Element) i.next();
			// On affiche le nom de l￿élément courant
			System.out.println(courant.getChild("nom").getText());
		}
	}

	public static void main(String[] args) {
		// On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try {
			// On crée un nouveau document JDOM avec en argument le fichier XML
			// Le parsing est terminé ;)
			document = sxb.build(new File("Exercice2.xml"));
		} catch (Exception e) {
		}
		// On initialise un nouvel élément racine avec l'élément racine du
		// document.
		racine = document.getRootElement();
		// Méthode définie dans la partie 3.2. de cet article
		afficheALL();
	}
}