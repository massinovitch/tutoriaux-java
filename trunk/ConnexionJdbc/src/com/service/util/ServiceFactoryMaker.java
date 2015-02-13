package com.service.util;

import com.constant.GeneralConstants;

/**
 * 
 * Service Factory Maker 
 * @author Rachid RAMI
 * 
 */
public class ServiceFactoryMaker {

	/**
	 * Empty constructor
	 */
	private ServiceFactoryMaker() {
		// Non intantiable contructor
	}

	/**
	 * Getting the good factory
	 * @param whichFactory : String
	 * @return AbstractServiceFactory
	 */
	public static AbstractServiceFactory getServiceFactory(String whichFactory) {
		AbstractServiceFactory abstractFactory = null;
		if (whichFactory.equals(GeneralConstants.MYSQL_DATABASE))
			abstractFactory = ServiceFactory.getInstance();
		return abstractFactory;
	}
}
