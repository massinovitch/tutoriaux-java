package com.service;

import java.util.List;

import com.exception.ApplicationException;
import com.model.Utilisateur;

public interface UtilisateurService {
	
	public void create(Utilisateur utilisateur) throws ApplicationException;

	public void update(Utilisateur utilisateur) throws ApplicationException;

	public Utilisateur findById(int id) throws ApplicationException;
	
	public Utilisateur findByCriteres(Utilisateur utilisateur) throws ApplicationException;
	
	public List<Utilisateur> findAll() throws ApplicationException;
	
	public void delete(int utilisateurId) throws ApplicationException;	
	
}
