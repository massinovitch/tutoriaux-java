package com.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.exception.ApplicationException;

public class ConnectionUtil {
	/**
	 * logger to display messages.
	 */
	private static final Logger LOGGER = Logger.getLogger(ConnectionUtil.class);

	/**
	 * Objet Connection
	 */
	private static Connection connect;
	
	/**
	 * Méthode qui va nous retourner notre instance
	 * et la créer si elle n'existe pas...
	 * @return
	 * @throws Exception 
	 */
	public static Connection getConnection() throws ApplicationException {
		try {
			if(connect == null || connect.isClosed()){
				Configuration configuration = Configuration.getInstance();
				Class.forName(configuration.getDriver());
				connect = DriverManager.getConnection(configuration.getUrl(), configuration.getUserName(), configuration.getPassword());
				connect.setAutoCommit(false);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new ApplicationException(e);
		}		
		return connect;	
	}	
	public static void commitTransaction() throws ApplicationException {
		try {
			if (connect != null && !connect.isClosed()) {
				connect.commit();		
				connect.close();
			}
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
			throw new ApplicationException(ex);
		}	
	}	
	public static void rollbackTransaction() throws ApplicationException {
		try {
			if (connect != null && !connect.isClosed()) {
				connect.rollback();
				connect.close();
			}
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
			throw new ApplicationException(ex);
		}		
	}	
	
	public static void closeResutlSet(ResultSet resultSet) throws ApplicationException {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException ex) {
				throw new ApplicationException(ex.getMessage());
			}
		}
	}
    

	public static void closePreparedStatement(PreparedStatement pStatement) throws ApplicationException {
		if (pStatement != null) {
			try {
				pStatement.close();
			} catch (SQLException ex) {
				throw new ApplicationException(ex.getMessage());
			}
		}
    }
}
