package com.persistence;

import java.util.List;

import com.exception.DAOException;
import com.model.Utilisateur;



public interface UtilisateurDao {
	
	public void create(Utilisateur utilisateur) throws DAOException;

	public void update(Utilisateur utilisateur) throws DAOException;

	public Utilisateur findById(int id) throws DAOException;
	
	public Utilisateur findByCriteres(Utilisateur utilisateur) throws DAOException;
	
	public List<Utilisateur> findAll() throws DAOException;
	
	public void delete(int utilisateurId) throws DAOException;	
}
