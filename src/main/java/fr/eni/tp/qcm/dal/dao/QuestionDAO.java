package fr.eni.tp.qcm.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.bo.SectionTest;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface QuestionDAO extends GenericDAO<Question, Integer>{
	
	Question resultSetToQuestion(ResultSet resultSet) throws SQLException;

	List<Question> selectByIdTheme(Integer idTest) throws DaoException;


}
