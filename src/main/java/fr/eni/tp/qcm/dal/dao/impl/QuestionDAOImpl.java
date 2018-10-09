package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.dal.dao.QuestionDAO;
import fr.eni.tp.qcm.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class QuestionDAOImpl implements QuestionDAO{
	
	private static final String SELECT_ALL_QUESTIONS_QUERY = "SELECT idQuestion, enonce, media, points FROM QUESTION";
    private static final String SELECT_ONE_QUESTION_QUERY = "SELECT idQuestion, enonce, media, points FROM QUESTION where idQuestion = ?";
    private static final String INSERT_QUESTION_QUERY = "INSERT INTO QUESTION(enonce, media, points, idTheme) VALUES (?, ?, ?, ?)";
    private static final String DELETE_QUESTION_QUERY = "DELETE FROM QUESTION WHERE idQuestion = ?";
    private static final String UPDATE_QUESTION_QUERY = "UPDATE QUESTION SET enonce = ?, media = ?, points = ?, idTheme = ? WHERE idQuestion = ?";

    private static QuestionDAOImpl instance;
    
    private QuestionDAOImpl() {
        
    }
    
    public static QuestionDAOImpl getInstance() {
        if(instance == null) {
            instance = new QuestionDAOImpl();
        }
        return instance;
    }
    
	@Override
	public Question insert(Question question) throws DaoException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    try {
	    	connection = MSSQLConnectionFactory.get();
	            
	        statement = connection.prepareStatement(INSERT_QUESTION_QUERY, Statement.RETURN_GENERATED_KEYS);
	            
	        statement.setString(1, question.getEnonce());
	        statement.setString(2, question.getMedia());
	        statement.setInt(3, question.getPoints());
	        statement.setInt(4, question.getTheme().getIdTheme());

	        if (statement.executeUpdate() == 1) {
	            resultSet = statement.getGeneratedKeys();
	            if (resultSet.next()) {
	            	question.setIdQuestion(resultSet.getInt(1));                    
	            }
	        }
	    } catch(SQLException e) {
	        throw new DaoException(e.getMessage(), e);
	    } finally {
	    	ResourceUtil.safeClose(resultSet, statement, connection);
	    }
	        
	    return question;
	}

	@Override
	public void update(Question question) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(UPDATE_QUESTION_QUERY);
            
            statement.setString(1, question.getEnonce());
	        statement.setString(2, question.getMedia());
	        statement.setInt(3, question.getPoints());
	        statement.setInt(4, question.getTheme().getIdTheme());
            statement.setInt(5, question.getIdQuestion());
            
            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(DELETE_QUESTION_QUERY);
            
            statement.setInt(1, id);
            
            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
		
	}

	@Override
	public Question selectById(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Question question = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_ONE_QUESTION_QUERY);
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	question = resultSetToQuestion(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return question;
	}

	@Override
	public List<Question> selectAll() throws DaoException {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Question> list = new ArrayList<>();
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_QUESTIONS_QUERY);

            while (resultSet.next()) {
                list.add(resultSetToQuestion(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return list;
	}
	
	private Question resultSetToQuestion(ResultSet resultSet) throws SQLException {
        
		Question question = new Question();
		question.setIdQuestion(resultSet.getInt("idQuestion"));
		question.setEnonce(resultSet.getString("enonce"));
        question.setMedia(resultSet.getString("media"));
        question.setPoints(resultSet.getInt("points"));
        
        return question;
        
    }

}
