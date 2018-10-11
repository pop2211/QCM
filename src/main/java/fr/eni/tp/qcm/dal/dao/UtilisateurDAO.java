package fr.eni.tp.qcm.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.web.common.dal.dao.GenericDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface UtilisateurDAO extends GenericDAO<Utilisateur, Integer> {
	
	Utilisateur connexion(String email, String password) throws DaoException;

	Utilisateur resultSetToUtilisateur(ResultSet resultSet) throws SQLException;
	
}
