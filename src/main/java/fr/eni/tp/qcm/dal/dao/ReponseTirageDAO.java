package fr.eni.tp.qcm.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.tp.qcm.bo.ReponseTirage;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface ReponseTirageDAO  extends GenericDAO<ReponseTirage, Integer>{

	ReponseTirage resultSetToReponseTirage(ResultSet resultSet) throws SQLException;

	void delete(Integer idProposition, Integer idQuestion, Integer idEpreuve) throws DaoException;

	List<ReponseTirage> selectByQuestionAndEpreuve(Integer idQuestion, Integer idEpreuve) throws DaoException;

	ReponseTirage selectByQuestionAndEpreuveAndProposition(Integer questionId, Integer epreuveId, Integer propositionId) throws DaoException;

}
