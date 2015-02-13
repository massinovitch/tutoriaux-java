/*
 * Cr�� le 2 f�vr. 2008
 *
 * Fen�tre - Pr�f�rences - Java - Style de code - Mod�les de code
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
