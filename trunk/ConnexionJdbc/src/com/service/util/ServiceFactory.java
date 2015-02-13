
package com.service.util;

import com.constant.GeneralConstants;
import com.persistence.util.DAOFactoryMaker;
import com.service.*;
import com.service.impl.*;


/**
 * This Factory is used when JNDI is used
 * 
 * @author Rachid RAMI
 * 
 */
 class ServiceFactory implements AbstractServiceFactory {
     
	/**
	 * Sole instance of the ServiceFactory
	 */
	private static ServiceFactory serviceFactory = null;
    
	/**
	 * Private Constructor 
	 */
	private ServiceFactory() {
		// You are not allowed to instantiate this class
	}
    
	/**
	 * Thread safe singleton
	 * @return AbstractServiceFactory
	 */
	public static synchronized ServiceFactory getInstance() {
		if (serviceFactory == null)
			serviceFactory = new ServiceFactory();
		return serviceFactory;
	}

    /**
     * Getting the Role Service
     * @return RoleService
     */
    public RoleService getRoleService() {
    	RoleServiceImpl roleService = RoleServiceImpl.getInstance();
    	roleService.setRoleDao(DAOFactoryMaker.getDAOFactory(GeneralConstants.MYSQL_DATABASE).getRoleDao());
        return roleService;
    }

    /**
     * Getting the Utilisateur Service
     * @return UtilisateurService
     */
    public UtilisateurService getUtilisateurService() {
    	UtilisateurServiceImpl utilisateurService = UtilisateurServiceImpl.getInstance();
    	utilisateurService.setRoleDao(DAOFactoryMaker.getDAOFactory(GeneralConstants.MYSQL_DATABASE).getRoleDao());
        utilisateurService.setUtilisateurDao(DAOFactoryMaker.getDAOFactory(GeneralConstants.MYSQL_DATABASE).getUtilisateurDao());
        return utilisateurService;
    }	
}