package com.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Principal {

	public void readFile(String fileName)
	throws IOException
	{
	  InputStream is = getClass().getResourceAsStream(fileName);
	  InputStreamReader isr = new InputStreamReader(is);
	  BufferedReader br = new BufferedReader(isr);
	  String line;
	  while ((line = br.readLine()) != null) 
	  {
	    System.out.println("line fichier : " + line);
	  }
	  br.close();
	  isr.close();
	  is.close();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Principal principal = new Principal();
			principal.readFile("/resources/file.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
