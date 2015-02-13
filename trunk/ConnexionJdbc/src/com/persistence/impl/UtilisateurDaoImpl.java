package com.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.constant.GeneralConstants;
import com.exception.DAOException;
import com.jdbc.connection.ConnectionUtil;
import com.model.Role;
import com.model.Utilisateur;
import com.persistence.UtilisateurDao;

public class UtilisateurDaoImpl implements UtilisateurDao {
	
	/**
	 * LOGGER
	 */
	private static final Logger LOGGER = Logger.getLogger(UtilisateurDaoImpl.class);

    /**
     * CREATE_QUERY
     */
    private static final String CREATE_QUERY = "INSERT INTO utilisateur ( IPN, ROLE_ID) VALUES (?, ?)";

    /**
     * DELETE_QUERY
     */
    private static final String DELETE_QUERY = "DELETE FROM utilisateur WHERE UTILISATEUR_ID = ?";    
    
	/**
	 * FIND_BY_ID
	 */
	private static final String FIND_BY_ID = "SELECT * FROM utilisateur WHERE UTILISATEUR_ID = ?";   
	
	/**
	 * FIND_ALL_QUERY
	 */
	private static final String FIND_ALL_QUERY = "SELECT * FROM utilisateur";
	
	/**
	 * UPDATE_QUERY
	 */
	private static final String UPDATE_QUERY = "UPDATE utilisateur SET IPN = ?, ROLE_ID = ? WHERE UTILISATEUR_ID = ?";
	
	/**
	 * FIND_UTILISATEUR
	 */
	private static final String FIND_UTILISATEUR = "SELECT * FROM utilisateur WHERE";//toujours laisser l'espace au debut pour un fragement de requete
	
	/**
	 * ATTRIBUT_IPN
	 */
	private static final String ATTRIBUT_IPN = " IPN =";
	
	/**
	 * ATTRIBUT_ROLE
	 */
	private static final String ATTRIBUT_ROLE = " ROLE_ID =";
	

	private static UtilisateurDaoImpl utilisateurDao;	
	
	private UtilisateurDaoImpl() {
		super();
	}
	
	public static synchronized UtilisateurDaoImpl getInstance() {
		if (utilisateurDao == null)
			utilisateurDao = new UtilisateurDaoImpl();
		return utilisateurDao;		
	}
		
	public void create(Utilisateur utilisateur) throws DAOException {
		LOGGER.debug("Begin UtilisateurDAO#create(Utilisateur utilisateur)");
		PreparedStatement statement = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			statement = connection.prepareStatement(CREATE_QUERY);
			LOGGER.debug(CREATE_QUERY);
			statement.setString(1, utilisateur.getIpn());
			statement.setInt(2, utilisateur.getRole().getRoleId());          
			statement.executeUpdate();
			LOGGER.debug("End UtilisateurDAO#create(Utilisateur utilisateur)");
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new DAOException(ex);
		} finally {
			ConnectionUtil.closePreparedStatement(statement);
		}
	}

	public void delete(int utilisateurId) throws DAOException {
		LOGGER.debug("Begin UtilisateurDAO#delete(int utilisateurId)");
        LOGGER.debug("Delete a utilisateur : " + utilisateurId);
		PreparedStatement prepStatement = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			LOGGER.debug("Query to execute : " + DELETE_QUERY);
			prepStatement = connection.prepareStatement(DELETE_QUERY);
			prepStatement.setInt(1, utilisateurId);
			prepStatement.executeUpdate();
			LOGGER.debug("End UtilisateurDAO#delete(int utilisateurId)");
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			ConnectionUtil.closePreparedStatement(prepStatement);
		}
	}

	public Utilisateur findById(int id) throws DAOException {
		LOGGER.debug("Begin UtilisateurDAO#findById(int id) ");
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			statement = connection.prepareStatement(FIND_BY_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
            List<Utilisateur> resultList = getUtilisateurList(resultSet);
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

	public void update(Utilisateur utilisateur) throws DAOException {
		LOGGER.debug("Begin UtilisateurDAO#update(Utilisateur utilisateur)");
		PreparedStatement prepStatement = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			LOGGER.debug("Query to execute : " + UPDATE_QUERY);
			prepStatement = connection.prepareStatement(UPDATE_QUERY);
			prepStatement.setString(1, utilisateur.getIpn());
			prepStatement.setInt(2, utilisateur.getRole().getRoleId());
			prepStatement.setInt(3, utilisateur.getUtilisateurId());
			prepStatement.executeUpdate();
			LOGGER.debug("End UtilisateurDAO#update(Utilisateur utilisateur)");
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
			throw new DAOException(ex);
		} finally {
			ConnectionUtil.closePreparedStatement(prepStatement);
		}
	}
	
	
	//cette méthode n'est pas bien faite. je revois ça. elle doit retourner une liste d'utilisateur au lieu d'un. elle aussi renvoyer faire une recherche en integrant une portion du mot avec une *
	public Utilisateur findByCriteres(Utilisateur utilisateur) throws DAOException {
		LOGGER.debug("Begin UtilisateurDAO#findByCriteres(Utilisateur utilisateur) ");
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		StringBuilder query = new StringBuilder(FIND_UTILISATEUR);
		try {
			Connection connection = ConnectionUtil.getConnection();

			if ( (utilisateur.getIpn() != null) && (!utilisateur.getIpn().equals("")) ) {
				query.append(ATTRIBUT_IPN);	
				query.append("'");
				query.append(utilisateur.getIpn());
				query.append("'");
			} else if (utilisateur.getRole().getRoleId() != 0) {
				query.append(GeneralConstants.AND);
				query.append(ATTRIBUT_ROLE);
				query.append(utilisateur.getRole().getRoleId());
			}	
			statement = connection.prepareStatement(query.toString());
			resultSet = statement.executeQuery();
            List<Utilisateur> resultList = getUtilisateurList(resultSet);
    		LOGGER.debug("End UtilisateurDAO#findByCriteres(Utilisateur utilisateur) ");
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
		
    private List<Utilisateur> getUtilisateurList(ResultSet resultSet) throws SQLException, DAOException {
        List<Utilisateur> resultList = new ArrayList<Utilisateur>();    
        Role role;
        while (resultSet.next()) {
        	Utilisateur utilisateur = new Utilisateur();
        	utilisateur.setUtilisateurId(resultSet.getInt("UTILISATEUR_ID"));
        	utilisateur.setIpn(resultSet.getString("IPN"));
        	role = new Role();
        	role.setRoleId(resultSet.getInt("ROLE_ID"));
        	utilisateur.setRole(role);
            resultList.add(utilisateur);
        }
        return resultList;
    }

	public List<Utilisateur> findAll() throws DAOException {
		LOGGER.debug("Begin UtilisateurDAO#findAll ");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = ConnectionUtil.getConnection();
			LOGGER.debug("Query to execute : " + FIND_ALL_QUERY);
			preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
			resultSet = preparedStatement.executeQuery();
			LOGGER.debug("End UtilisateurDAO#findAll");
            return getUtilisateurList(resultSet);
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
			throw new DAOException(ex);
		} finally {
			ConnectionUtil.closeResutlSet(resultSet);
			ConnectionUtil.closePreparedStatement(preparedStatement);
		}
	}
}
