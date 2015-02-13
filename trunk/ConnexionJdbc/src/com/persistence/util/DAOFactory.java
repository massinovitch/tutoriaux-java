/*
 * Créé le 2 févr. 2008
 *
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package com.persistence.util;

import com.persistence.*;
import com.persistence.impl.*;

class DAOFactory implements AbstractDAOFactory {
    
	/**
	 * AbstractDAOFactory instance
	 */
	private static DAOFactory factory;

	/**
	 * Non instantiable constructor.
	 * 
	 */
	private DAOFactory() {
		// Non instantiable constructor.
	}

	/**
	 * This method implements a Singleton wich allows to construct the unique
	 * instance of the class <code>DAOFactory</code>.
	 * 
	 * @return the unique instance of the class <code>DAOFactory</code>.
	 */
	public static synchronized DAOFactory getInstance() {
		if (factory == null)
			factory = new DAOFactory();
		return factory;
	}


	public RoleDao getRoleDao() {
		RoleDaoImpl roleDao = RoleDaoImpl.getInstance();
		return roleDao;
	}

	public UtilisateurDao getUtilisateurDao() {
		UtilisateurDaoImpl utilisateurDao = UtilisateurDaoImpl.getInstance();
		return utilisateurDao;
	}
    
}
