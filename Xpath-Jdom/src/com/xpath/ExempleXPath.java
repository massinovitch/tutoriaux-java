
package com.xpath;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

/**
 *
 * @author ZedroS
 */
public class ExempleXPath {
    
    /** Creates a new instance of ExempleXPath */
    public ExempleXPath() {
    }

    /** Parse le fichier passé en entrée pour lire le nom de chaque patient. 
     * Pour cela, on cherche les patients puis, à partir de leur identifiant, on cherche leur nom. 
     *
     *@ params Nom du fichier à lire.
     */
    void parse(File _FilePath) {
        org.jdom.Document document = null ;
        try {
            /* On crée une instance de SAXBuilder */
            SAXBuilder sxb = new SAXBuilder();
            document = sxb.build(_FilePath);
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier " 
				+ e.getMessage() );
            e.printStackTrace();
        } catch (JDOMException e){
            System.out.println("Erreur lors de la construction du fichier JDOM " 
				+ e.getMessage() );
            e.printStackTrace();
        }
        
        try {
            /* On initialise un nouvel élément avec l'élément racine du
               document. */
            Element racine = document.getRootElement();
            
            /* On va dans un premier temps rechercher l'ensemble des noms 
               des patients de notre hôpital. */
            
            /* Recherche de la liste des patients         */
            XPath xpa = XPath.newInstance("//patient");   
            
            /* On récupère tous les noeuds répondant au chemin //patient */
            List results = xpa.selectNodes(racine) ;
            
            Iterator iter = results.iterator() ;
            
            Element noeudCourant = null;
            String personneId = null ;
            while (iter.hasNext()){
                /* Pour chaque patient nous allons chercher son nom puis l'afficher */
                noeudCourant = (Element) iter.next();
                
                /* On récupère l'identifiant de la personne                
                   Noter le . en début du chemin : on part de la position courante 
                   le @ indique que l'on cherche un attribut               */
                xpa = XPath.newInstance("./@personneId");
                personneId = xpa.valueOf(noeudCourant);
                
                /* A partir de là on récupère les infos dans la balise personne correspondante 
                   On spécifie que l'on recherche une balise en fonction de la valeur 
                   d'un de ses attributs :*/
                xpa = XPath.newInstance("//personne[@id='" + personneId + "']");
                noeudCourant = (Element) xpa.selectSingleNode(noeudCourant);
                
                /* Nous cherchons à présent la valeur de la balise nom :                */
                xpa = XPath.newInstance("./nom");
                System.out.println("Valeur : " + xpa.valueOf(noeudCourant));
            }
        } catch (JDOMException e) {
            System.out.println("Erreur JDOM " + e.getMessage() );
            e.printStackTrace();            
        } 
    }
}