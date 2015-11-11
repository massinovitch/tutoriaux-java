package com.wordnet;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.properties.GeneralConstants;

public class FileWordnetDomaine {
	
	/* construit à partir du fichier wn-domains-3.2-20070223 une map (clé, nomDuDomaine)*/
	public Map<String, String> getMapDomaineNames(String filePath, List<String> listOntologies)
			throws IOException {
		Map<String, String> mapDomaines = new HashMap<String, String>();
		String sCurrentLine;
		InputStream ips = new FileInputStream(filePath);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String[] lineOfDomaineNames;
		String keyDomaine;
		while ((sCurrentLine = br.readLine()) != null) {
			sCurrentLine = sCurrentLine.trim();			
			lineOfDomaineNames = sCurrentLine.split(GeneralConstants.SEPARATEUR_ESPACE); 
			keyDomaine = lineOfDomaineNames[0].replace('s', 'a');
			for ( int i = 1; i < lineOfDomaineNames.length; i++) {
				String currentOntologie = lineOfDomaineNames[i].trim();
				boolean isOntologiePresent = listOntologies.contains(currentOntologie);
				if ( isOntologiePresent ) {
					String valueForKey = mapDomaines.get(keyDomaine);
					if ( valueForKey != null) {
						valueForKey += " " + currentOntologie;
					} else {
						valueForKey = currentOntologie;
					}
					mapDomaines.put(keyDomaine, valueForKey);		
				}
			}
		}
		br.close();
		return mapDomaines;
	}
	
	/* construit à partir du fichier wn-domains-3.2-20070223 un set de nom de domaines*/
	public Set<String> getSetDomaineNames(String filePath)
			throws IOException {
		Set<String> setDomaines = new TreeSet<String>();
		String sCurrentLine;
		InputStream ips = new FileInputStream(filePath);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		while ((sCurrentLine = br.readLine()) != null) {
			sCurrentLine = sCurrentLine.trim();			
			String[] lineOfDomaineNames = sCurrentLine.split(GeneralConstants.SEPARATEUR_ESPACE); 
			setDomaines.add(lineOfDomaineNames[1]);			
		}
		br.close();
		return setDomaines;
	}	
	
	public void saveCollection(Collection<String> collection, String filePath) throws IOException {
	      OutputStream file = new FileOutputStream(filePath);
	      OutputStream buffer = new BufferedOutputStream(file);
	      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(buffer));
	      Iterator<String> iterator = collection.iterator();
	      while (iterator.hasNext()) {
	    	  String domaine = iterator.next();
	    	  bw.write(domaine);
	    	  bw.newLine();
	      }
	      bw.close();
	}	
}
