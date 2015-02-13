package com.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.constant.GeneralConstants;
import com.exception.DAOException;
import com.jdbc.connection.ConnectionUtil;
import com.model.Role;
import com.model.Utilisateur;
import com.service.UtilisateurService;
import com.service.util.ServiceFactoryMaker;

public class UtilisateurServiceJunitTest {
	/**
	 * Service used for testing
	 */
	private static UtilisateurService utilisateurService = ServiceFactoryMaker.getServiceFactory(GeneralConstants.MYSQL_DATABASE).getUtilisateurService();

	private Utilisateur utilisateur;
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
		utilisateur = new Utilisateur();
		role = new Role();
    }    
    
    @After
    public void tearDown() {
    	//execution après chaque methode
    }        
     
    @Test
    public void createUtilisateur() throws DAOException {
		utilisateur.setIpn("utilisateur1");
		role.setRoleId(1);
		utilisateur.setRole(role);
		utilisateurService.create(utilisateur);
		Utilisateur Result = utilisateurService.findById(1);
		assertEquals(utilisateur.getIpn(), Result.getIpn());
    }
    
    @Test    
    public void updateUtilisateur() throws DAOException {
		utilisateur = utilisateurService.findById(3);
		utilisateur.setIpn("utilisateur2");
		utilisateurService.update(utilisateur);
		utilisateur = utilisateurService.findById(3);
		assertEquals(utilisateur.getIpn(), "utilisateur2");
    } 
}
