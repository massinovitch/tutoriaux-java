package com.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	private static final String fileName = "resources/constants.properties"; //$NON-NLS-1$	

	private static Configuration _instance = null;

	private Properties props = null;

	private Configuration() {
	         props = new Properties();
	    	try {
		    FileInputStream fis = new FileInputStream(new File(fileName));
		    props.load(fis);
	    	}
	    	catch (Exception e) {
	    	    // catch Configuration Exception right here
	    	}
	    }

	public synchronized static Configuration getInstance() {
		if (_instance == null)
			_instance = new Configuration();
		return _instance;
	}

	// get property value by name
	public String getProperty(String key) {
		String value = null;
		if (props.containsKey(key))
			value = (String) props.get(key);
		else {
			// the property is absent
		}
		return value;
	}
	
	public static void main(String[] args) throws IOException {

		String fileNameDomaine = Configuration.getInstance().getProperty("entete.compo");
		System.out.println(fileNameDomaine);

	}
	

}