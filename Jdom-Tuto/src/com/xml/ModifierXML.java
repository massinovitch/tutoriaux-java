package com.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ModifierXML {
	static Document document;
	static Element racine;

	public static void main(String[] args) {
		try {
			lireFichier("Exercice 2.xml");
			supprElement("prenoms");
			enregistreFichier("Exercice 2.xml");
		} catch (Exception e) {
		}
	}

	// On parse le fichier et on initialise la racine de
	// notre arborescence
	static void lireFichier(String fichier) throws Exception {
		SAXBuilder sxb = new SAXBuilder();
		document = sxb.build(new File(fichier));
		racine = document.getRootElement();
	}

	// On fait des modifications sur un Element
	static void supprElement(String element) {
		// Dans un premier temps on liste tous les étudiants
		List listEtudiant = racine.getChildren("etudiant");
		Iterator i = listEtudiant.iterator();
		// On parcours la liste grâce à un iterator
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			// Si l￿étudiant possède l'Element en question on applique
			// les modifications.
			if (courant.getChild(element) != null) {
				// On supprime l'Element en question
				courant.removeChild(element);
				// On renomme l'Element père sachant qu'une balise XML n'accepte
				// ni les espaces ni les caractères spéciaux
				// "étudiant modifié" devient "etudiant_modifie"
				courant.setName("etudiant_modifie");
			}
		}
	}

	// On enregistre notre nouvelle arborescence dans le fichier
	// d'origine dans un format classique.
	static void enregistreFichier(String fichier) throws Exception {
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(document, new FileOutputStream(fichier));
	}
}
