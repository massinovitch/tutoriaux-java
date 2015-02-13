/*
 * Cr�� le 2 f�vr. 2008
 *
 * Fen�tre - Pr�f�rences - Java - Style de code - Mod�les de code
 */
package com.persistence.util;

import com.persistence.*;

public interface AbstractDAOFactory {

	public RoleDao getRoleDao();

	public UtilisateurDao getUtilisateurDao();

}