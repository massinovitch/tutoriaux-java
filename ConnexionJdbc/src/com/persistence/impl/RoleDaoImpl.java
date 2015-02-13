package com.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.exception.DAOException;
import com.jdbc.connection.ConnectionUtil;
import com.model.Role;
import com.persistence.RoleDao;

public class RoleDaoImpl implements RoleDao {
	
	/**
	 * LOGGER
	 */
	private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

    /**
     * CREATE_QUERY
     */
    private static final String CREATE_QUERY = "INSERT INTO role (ROLE_NOM) VALUES (?)";

    /**
     * DELETE_QUERY
     */
    private static final String DELETE_QUERY = "DELETE FROM role WHERE ROLE_ID = ?";    
    
	/**
	 * FIND_BY_ID
	 */
	private static final String FIND_BY_ID = "SELECT * FROM role WHERE ROLE_ID = ?";   
	
	/**
	 * FIND_ALL_QUERY
	 */
	private static final String FIND_ALL_QUERY = "SELECT * FROM role";
	
	/**
	 * UPDATE_QUERY
	 */
	private static final String UPDATE_QUERY = "UPDATE role SET ROLE_NOM = ? WHERE ROLE_ID = ?";

	private static RoleDaoImpl roleDao;
	
	private RoleDaoImpl() {
		super();
	}
	
	public static synchronized RoleDaoImpl getInstance() {
		if (roleDao == null)
			roleDao = new RoleDaoImpl();
		return roleDao;		
	}	
	
	public void create(Role role) throws DAOException {
		LOGGER.debug("Begin RoleDAO#create(Role role)");
		PreparedStatement statement = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			statement = connection.prepareStatement(CREATE_QUERY);
			LOGGER.debug(CREATE_QUERY);
			statement.setString(1, role.getNom());       
			statement.executeUpdate();
			LOGGER.debug("End RoleDAO#create(Role role)");
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new DAOException(ex);
		} finally {
			ConnectionUtil.closePreparedStatement(statement);
		}
		
	}

	public void delete(int roleId) throws DAOException {
		LOGGER.debug("Begin RoleDAO#delete(int roleId)");
        LOGGER.debug("Delete a serveur : " + roleId);
		PreparedStatement prepStatement = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			LOGGER.debug("Query to execute : " + DELETE_QUERY);
			prepStatement = connection.prepareStatement(DELETE_QUERY);
			prepStatement.setInt(1, roleId);
			prepStatement.executeUpdate();
			LOGGER.debug("End RolerDAO#delete(int roleId)");
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			ConnectionUtil.closePreparedStatement(prepStatement);
		}
		
	}

	public Role findById(int id) throws DAOException {
		LOGGER.debug("Begin ServeurDAO#findById(int id) ");
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			statement = connection.prepareStatement(FIND_BY_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
            List<Role> resultList = getRoleList(resultSet);
    		LOGGER.debug("End UtilisateurDAO#findById(int id) ");
			if (resultList.size() != 0)
				return resultList.get(0);
			else
				return null;
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
			throw new DAOException(ex);
		} finally {
			ConnectionUtil.closeResutlSet(resultSet);
			ConnectionUtil.closePreparedStatement(statement);
		}
	}

	public void update(Role role) throws DAOException {
		LOGGER.debug("Begin RoleDAO#update(Role role)");
		PreparedStatement prepStatement = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			LOGGER.debug("Query to execute : " + UPDATE_QUERY);
			prepStatement = connection.prepareStatement(UPDATE_QUERY);
			prepStatement.setString(1, role.getNom());
			prepStatement.setInt(2, role.getRoleId());
			prepStatement.executeUpdate();
			LOGGER.debug("End RoleDAO#update(Role role)");
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
			throw new DAOException(ex);
		} finally {
			ConnectionUtil.closePreparedStatement(prepStatement);
		}
		
	}

	public List<Role> findAll() throws DAOException {
		LOGGER.debug("Begin RoleDAO#findAll ");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			LOGGER.debug("Query to execute : " + FIND_ALL_QUERY);
			preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
			resultSet = preparedStatement.executeQuery();
			LOGGER.debug("End RoleDAO#findAll");
            return getRoleList(resultSet);
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
			throw new DAOException(ex);
		} finally {
			ConnectionUtil.closeResutlSet(resultSet);
			ConnectionUtil.closePreparedStatement(preparedStatement);
		}
	}
	
	private List<Role> getRoleList(ResultSet resultSet) throws SQLException, DAOException {
        List<Role> resultList = new ArrayList<Role>();        
        while (resultSet.next()) {
        	Role role = new Role();
        	role.setRoleId(resultSet.getInt("ROLE_ID"));
        	role.setNom(resultSet.getString("ROLE_NOM"));
            resultList.add(role);
        }
        return resultList;
    }
}

