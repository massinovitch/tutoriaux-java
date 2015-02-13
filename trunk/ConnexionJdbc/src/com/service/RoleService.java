package com.service;

import java.util.List;

import com.exception.ApplicationException;
import com.model.Role;

public interface RoleService {
	public void create(Role role) throws ApplicationException;

	public void update(Role role) throws ApplicationException;

	public Role findById(int id) throws ApplicationException;
	
	public List<Role> findAll() throws ApplicationException;
	
	public void delete(int roleId) throws ApplicationException;	
}
