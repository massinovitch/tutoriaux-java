package com.persistence.util;

import com.constant.GeneralConstants;


/**
 * 
 * DAO Factory maker
 * 
 * @author Rachid RAMI
 * 
 */
public class DAOFactoryMaker {
	
	/**
	 * Empty constructor
	 * 
	 */
	private DAOFactoryMaker() {
		// You are not allowed ot instantiate this class
	}

	/**
	 * Getting the good factory
	 * 
	 * @param whichFactory
	 *            the type of the factory
	 * @return the good factory
	 */
	public static AbstractDAOFactory getDAOFactory(String whichFactory) {

		AbstractDAOFactory abstractFactory = null;

		if (whichFactory.equals(GeneralConstants.MYSQL_DATABASE)) {
			abstractFactory = DAOFactory.getInstance();
		}
		return abstractFactory;

	}

}
