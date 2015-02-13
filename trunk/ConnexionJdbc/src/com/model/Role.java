package com.model;

import com.util.BaseObject;

public class Role extends BaseObject {

	private static final long serialVersionUID = 1L;
	
	private int roleId;
	
	private String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
