package com.service.impl;

import java.util.List;

import com.exception.ApplicationException;
import com.exception.DAOException;
import com.model.Role;
import com.model.Utilisateur;
import com.persistence.RoleDao;
import com.persistence.UtilisateurDao;
import com.service.UtilisateurService;

public class UtilisateurServiceImpl implements UtilisateurService {

	/**
	 * DAO used by the service
	 */
	private UtilisateurDao utilisateurDao;

	private RoleDao roleDao;

	/**
	 * Singleton instance of PieceServiceImpl
	 */
	private static UtilisateurServiceImpl utilisateurService;

	/**
	 * Constructor for UtilisateurServiceImpl
	 */
	private UtilisateurServiceImpl() {
		super();
	}

	/**
	 * Getter for singleton instance
	 * 
	 * @return UtilisateurServiceImpl
	 */
	public static synchronized UtilisateurServiceImpl getInstance() {
		if (utilisateurService == null)
			utilisateurService = new UtilisateurServiceImpl();
		return utilisateurService;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public UtilisateurDao getUtilisateurDao() {
		return utilisateurDao;
	}

	public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public void create(Utilisateur utilisateur) throws ApplicationException {
		try {
			utilisateurDao.create(utilisateur);
		} catch (DAOException ex) {
			throw new ApplicationException(ex);
		}
	}

	public void delete(int utilisateurId) throws ApplicationException {
		try {
			utilisateurDao.delete(utilisateurId);
		} catch (DAOException ex) {
			throw new ApplicationException(ex);
		}
	}

	public List<Utilisateur> findAll() throws ApplicationException {
		try {
			List<Utilisateur> listUtilisateur = utilisateurDao.findAll();
			Role role;
			for (Utilisateur utilisateur : listUtilisateur) {
				role = utilisateur.getRole();
				role = roleDao.findById(role.getRoleId());
				utilisateur.setRole(role);
			}
			return listUtilisateur;
		} catch (DAOException ex) {
			throw new ApplicationException(ex);
		}
	}
	
	//a revoir, ne pas utiliser comme ça(voir dao)
	public Utilisateur findByCriteres(Utilisateur utilisateur)
			throws ApplicationException {
		try {
			return utilisateurDao.findByCriteres(utilisateur);
		} catch (DAOException ex) {
			throw new ApplicationException(ex);
		}
	}

	public Utilisateur findById(int id) throws ApplicationException {
		try {
			Utilisateur utilisateur = utilisateurDao.findById(id);
			if ( utilisateur != null) {
				Role role = utilisateur.getRole();
				role = roleDao.findById(role.getRoleId());
				utilisateur.setRole(role);				
			}
			return utilisateur;
		} catch (DAOException ex) {
			throw new ApplicationException(ex);
		}
	}

	public void update(Utilisateur utilisateur) throws ApplicationException {
		try {
			utilisateurDao.update(utilisateur);
		} catch (DAOException ex) {
			throw new ApplicationException(ex);
		}
	}

}
