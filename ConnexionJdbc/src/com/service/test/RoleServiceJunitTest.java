/*
 * Créé le 28 janv. 2008
 *
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package com.service.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.constant.GeneralConstants;
import com.exception.DAOException;
import com.jdbc.connection.ConnectionUtil;
import com.model.Role;
import com.service.RoleService;
import com.service.util.ServiceFactoryMaker;

/**
 * @author Laurent BOURGEOIS
 * 
 * 
 */
public class RoleServiceJunitTest {

	/**
	 * Service used for testing
	 */
	private static RoleService roleService = ServiceFactoryMaker.getServiceFactory(GeneralConstants.MYSQL_DATABASE).getRoleService();

	private Role role;
	
    @BeforeClass
    public static void oneTimeSetUp() {
        // execution tout au debut   
    }
    
    @AfterClass
    public static void oneTimeTearDown() {
    	//execution toute à la fin
		ConnectionUtil.commitTransaction();
    }    
    
    @Before
    public void setUp() {
    	//execution avant chaque methode
		role = new Role();
    }    
    
    @After
    public void tearDown() {
    	//execution après chaque methode
    }        
     
    @Test
    public void createRole() throws DAOException {
		role.setNom("role1");
		roleService.create(role);
		Role Result = roleService.findById(1);
		assertEquals(role.getNom(), Result.getNom());
    }
    
    @Test    
    public void updateRole() throws DAOException {
		role = roleService.findById(3);
		role.setNom("role2");
		roleService.update(role);
		role = roleService.findById(3);
		assertEquals(role.getNom(), "role2");
    }   
	
}
