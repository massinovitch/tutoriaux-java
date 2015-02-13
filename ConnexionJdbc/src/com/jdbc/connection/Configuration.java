/*
 * Créé le 25 janv. 2008
 *
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package com.jdbc.connection;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * Data Base configuration class
 * @author Rachid RAMI
 */
public final class Configuration {
	
	/**
	 * The config file path "C:\\workspace\\3dmWeb\\src\\resources\\Configuration.xml" pour 3dmweb(testes)
	 */
	private final static String CONFIG_FILE = "resources" + File.separator + "Configuration.xml";

	/**
	 * the configuration
	 */
	private static Configuration configuration;

	/**
	 * the url
	 */
	private String url;

	/**
	 * the user name
	 */
	private String userName;

	/**
	 * the password
	 */
	private String password;

	/**
	 * driver
	 */
	private String driver;

	/**
	 * Thread safe singleton
	 * @return a configuration
	 * @throws Exception thrown when there are prolems while reading configuration file
	 */
	public static synchronized Configuration getInstance() throws Exception {
		if (configuration == null) {
			configuration = new Configuration();
		}
		return configuration;
	}

	/**
	 * override toString
	 * @return a string
	 */
	public String toString() {
		return "Driver : " + driver + "\nUrl : " + url + "\nUserName : " + userName + "\nPassWord : " + password;
	}

	/**
	 * Listing childrens
	 * @param current the current elemnt
	 * @param depth the depth in the xml file
	 */
	private void listChildren(Element current, int depth) {

		if (depth != 0) {
			if (current.getName().trim().equalsIgnoreCase("driver"))
				driver = current.getValue().trim();
			else if (current.getName().trim().equalsIgnoreCase("url"))
				url = current.getValue().trim();
			else if (current.getName().trim().equalsIgnoreCase("userName"))
				userName = current.getValue().trim();
			else if (current.getName().trim().equalsIgnoreCase("password"))
				password = current.getValue().trim();
		}
		List children = current.getChildren();
		Iterator iterator = children.iterator();
		while (iterator.hasNext()) {
			Element child = (Element) iterator.next();
			listChildren(child, depth + 1);
		}

	}

	/**
	 * private constructor
	 * @throws Exception thrown when configuration file not found 
	 */
	private Configuration() throws Exception {
		Document document = new SAXBuilder().build(new File(CONFIG_FILE));
		Element root = document.getRootElement();
		listChildren(root, 0);
	}

	/**
	 * Getting password 
	 * @return Renvoie password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Getting url
	 * @return Renvoie url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Getting userName
	 * @return Renvoie userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Getting driver 
	 * @return Renvoie driver.
	 */
	public String getDriver() {
		return driver;
	}

}