package com.service.impl;

import java.util.List;


import com.exception.ApplicationException;
import com.exception.DAOException;
import com.model.Role;
import com.persistence.RoleDao;
import com.service.RoleService;

public class RoleServiceImpl implements RoleService {
	
	/**
	 * DAO used by the service
	 */
	private RoleDao roleDao;
	
	/**
	 * Singleton instance of PieceServiceImpl
	 */
	private static RoleServiceImpl roleService;

	/**
	 * Constructor for RoleServiceImpl
	 */
	private RoleServiceImpl() {
		super();
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/**
	 * Getter for singleton instance
	 * @return RoleServiceImpl
	 */
	public static synchronized RoleServiceImpl getInstance() {
		if (roleService == null)
			roleService = new RoleServiceImpl();
		return roleService;
	}	

	public void create(Role role) throws ApplicationException {
		try {
			roleDao.create(role);
		} catch (DAOException ex) {
			throw new ApplicationException(ex);
		}
	}

	public void delete(int roleId) throws ApplicationException {
		try {
			roleDao.delete(roleId);
		} catch (DAOException ex) {
			throw new ApplicationException(ex);
		}	
	}

	public List<Role> findAll() throws ApplicationException {
		try {
			return roleDao.findAll();
		} catch (DAOException ex) {
			throw new ApplicationException(ex);
		}	
	}

	public Role findById(int id) throws ApplicationException {
		try {
			return roleDao.findById(id);
		} catch (DAOException ex) {
			throw new ApplicationException(ex);
		}
	}

	public void update(Role role) throws ApplicationException {
		try {
			roleDao.update(role);
		} catch (DAOException ex) {
			throw new ApplicationException(ex);
		}
		
	}

}
