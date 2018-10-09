package fr.eni.tp.qcm.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.tp.qcm.bo.Question;

public interface QuestionDAO extends GenericDAO<Question, Integer>{
	
	Question resultSetToQuestion(ResultSet resultSet) throws SQLException;
	
	

}
