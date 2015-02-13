package com.model;

import com.util.BaseObject;


public class Utilisateur extends BaseObject {

	private static final long serialVersionUID = 1L;
		
	private int utilisateurId;
	
	private String ipn;
	
	private Role role;

	public String getIpn() {
		return ipn;
	}

	public void setIpn(String ipn) {
		this.ipn = ipn;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(int utilisateurId) {
		this.utilisateurId = utilisateurId;
	}
}
