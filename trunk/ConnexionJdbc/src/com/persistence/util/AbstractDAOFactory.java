/*
 * Créé le 2 févr. 2008
 *
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package com.persistence.util;

import com.persistence.*;

public interface AbstractDAOFactory {

	public RoleDao getRoleDao();

	public UtilisateurDao getUtilisateurDao();

}