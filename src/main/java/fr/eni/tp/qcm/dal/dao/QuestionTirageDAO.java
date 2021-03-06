package fr.eni.tp.qcm.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.tp.qcm.bo.QuestionTirage;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface QuestionTirageDAO extends GenericDAO<QuestionTirage, Integer>{

	List<QuestionTirage> selectByIdEpreuve(Integer id) throws DaoException;

	QuestionTirage resultSetToQuestionTirage(ResultSet resultSet) throws SQLException;

	QuestionTirage selectById(Integer epreuveId, Integer questionId) throws DaoException;

}
