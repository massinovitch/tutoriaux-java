package com.persistence.test;

import static org.junit.Assert.*;

import org.junit.*;

import com.constant.GeneralConstants;
import com.exception.DAOException;
import com.jdbc.connection.ConnectionUtil;
import com.model.Role;
import com.model.Utilisateur;
import com.persistence.UtilisateurDao;
import com.persistence.util.DAOFactoryMaker;


public class UtilisateurDaoJunitTest {
	private Utilisateur utilisateur;
	private Role role;
	UtilisateurDao utilisateurDao = DAOFactoryMaker.getDAOFactory(GeneralConstants.MYSQL_DATABASE).getUtilisateurDao();	
	
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
		utilisateur.setIpn("exemple1");
		role.setRoleId(1);
		utilisateur.setRole(role);
		utilisateurDao.create(utilisateur);
		Utilisateur utilisateurResult = utilisateurDao.findByCriteres(utilisateur);
		assertEquals(utilisateur.getIpn(), utilisateurResult.getIpn());
    }
    
    @Test    
    public void updateUtilisateur() throws DAOException {
		utilisateur = new Utilisateur();
		utilisateur.setUtilisateurId(1);
		utilisateur.setIpn("exemple2");
		role = new Role();
		role.setRoleId(2);
		utilisateur.setRole(role);			
		utilisateurDao.update(utilisateur);
		Utilisateur utilisateurResult = utilisateurDao.findByCriteres(utilisateur);
		assertEquals(utilisateur.getIpn(), utilisateurResult.getIpn());
    }    
}
