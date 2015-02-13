package com.persistence;

import java.util.List;

import com.exception.DAOException;
import com.model.Role;

public interface RoleDao {

	public void create(Role role) throws DAOException;

	public void update(Role role) throws DAOException;

	public Role findById(int id) throws DAOException;
	
	public List<Role> findAll() throws DAOException;
	
	public void delete(int roleId) throws DAOException;	
}
