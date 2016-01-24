package com.arbre;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.properties.GeneralConstants;
import com.similarity.SynsetSimilarity;

public class LireArbreXML {
	
	private Element root;
	
	//renvoyer la racine du fichier xml
    public LireArbreXML(String _FilePath) throws JDOMException, IOException {
        /* On crée une instance de SAXBuilder */
    	File entryFile = new File(_FilePath);
        SAXBuilder sxb = new SAXBuilder();
        Document document = sxb.build(entryFile);
        root = document.getRootElement();
    }
    
    private void updateBrancheSynset(SynsetSimilarity s) throws JDOMException {
    	 String numero = s.getNumero().split(GeneralConstants.SEPARATEUR_TIRET)[0];
    	 XPath xpa = XPath.newInstance(".//noeud[@synset='" + numero + "']");
         /* On récupère tous les noeuds répondant au chemin //patient */
         List results = xpa.selectNodes(root) ;
         Iterator iter = results.iterator() ;
         while (iter.hasNext()) {
        	 Element noeudCourant = (Element) iter.next();
        	 String numeroBranche = noeudCourant.getAttributeValue("branche");
        	 System.out.println("				" + numeroBranche);	
        	 s.getListNumeroBranche().add(numeroBranche);
         }
    }
    
    public void brancheSyn(List<SynsetSimilarity> Ti) throws JDOMException {
    	for (int i = 0; i < Ti.size(); i++) {
    		SynsetSimilarity s = Ti.get(i);
    		System.out.println("		branches du synset : " + s.getNumero() + " " + s.getNom());	
    		updateBrancheSynset(s); 		
    	}
    }
}
