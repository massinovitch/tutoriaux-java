package com.service.util;

import com.service.*;

/**
 * 
 * The abstract class <code>AbstractServiceFactory</code>is the super classe
 * of classes <code>ServiceFactory</code> and <code>TestServiceFactory</code>.
 * 
 * Subclasses of <code>AbstractServiceFactory</code> must provide definitions
 * for all its methods
 * 
 * @author Massinissa
 * 
 * 
 * 
 */
public interface AbstractServiceFactory {

    /**
     * @return RoleService
     */
    public RoleService getRoleService();

    /**
     * @return UtilisateurService
     */
    public UtilisateurService getUtilisateurService();
}