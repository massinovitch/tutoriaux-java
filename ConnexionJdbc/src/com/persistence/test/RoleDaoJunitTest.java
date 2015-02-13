package com.persistence.test;

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
import com.persistence.RoleDao;
import com.persistence.util.DAOFactoryMaker;

public class RoleDaoJunitTest {
	private Role role;
	RoleDao roleDao = DAOFactoryMaker.getDAOFactory(GeneralConstants.MYSQL_DATABASE).getRoleDao();	
	
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
		roleDao.create(role);
		Role Result = roleDao.findById(1);
		assertEquals(role.getNom(), Result.getNom());
    }
    
    @Test    
    public void updateRole() throws DAOException {
		role = roleDao.findById(3);
		role.setNom("role2");
		roleDao.update(role);
		role = roleDao.findById(3);
		assertEquals(role.getNom(), "role2");
    }    

}
