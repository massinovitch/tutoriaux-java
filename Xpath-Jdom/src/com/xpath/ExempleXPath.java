
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

    /** Parse le fichier pass� en entr�e pour lire le nom de chaque patient. 
     * Pour cela, on cherche les patients puis, � partir de leur identifiant, on cherche leur nom. 
     *
     *@ params Nom du fichier � lire.
     */
    void parse(File _FilePath) {
        org.jdom.Document document = null ;
        try {
            /* On cr�e une instance de SAXBuilder */
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
            /* On initialise un nouvel �l�ment avec l'�l�ment racine du
               document. */
            Element racine = document.getRootElement();
            
            /* On va dans un premier temps rechercher l'ensemble des noms 
               des patients de notre h�pital. */
            
            /* Recherche de la liste des patients         */
            XPath xpa = XPath.newInstance("//patient");   
            
            /* On r�cup�re tous les noeuds r�pondant au chemin //patient */
            List results = xpa.selectNodes(racine) ;
            
            Iterator iter = results.iterator() ;
            
            Element noeudCourant = null;
            String personneId = null ;
            while (iter.hasNext()){
                /* Pour chaque patient nous allons chercher son nom puis l'afficher */
                noeudCourant = (Element) iter.next();
                
                /* On r�cup�re l'identifiant de la personne                
                   Noter le . en d�but du chemin : on part de la position courante 
                   le @ indique que l'on cherche un attribut               */
                xpa = XPath.newInstance("./@personneId");
                personneId = xpa.valueOf(noeudCourant);
                
                /* A partir de l� on r�cup�re les infos dans la balise personne correspondante 
                   On sp�cifie que l'on recherche une balise en fonction de la valeur 
                   d'un de ses attributs :*/
                xpa = XPath.newInstance("//personne[@id='" + personneId + "']");
                noeudCourant = (Element) xpa.selectSingleNode(noeudCourant);
                
                /* Nous cherchons � pr�sent la valeur de la balise nom :                */
                xpa = XPath.newInstance("./nom");
                System.out.println("Valeur : " + xpa.valueOf(noeudCourant));
            }
        } catch (JDOMException e) {
            System.out.println("Erreur JDOM " + e.getMessage() );
            e.printStackTrace();            
        } 
    }
}