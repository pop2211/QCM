package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.tp.qcm.bo.QuestionTirage;
import fr.eni.tp.qcm.dal.dao.QuestionTirageDAO;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.qcm.dal.dao.ThemeDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class QuestionTirageDAOImpl implements QuestionTirageDAO{


    private static final String SELECT_QUESTION_TIRAGE_QUERY = "SELECT * FROM QUESTION_TIRAGE qt INNER JOIN QUESTION q ON qt.idQuestion = q.idQuestion INNER JOIN EPREUVE e ON qt.idEpreuve = e.idEpreuve WHERE idQuestionTirage = ?";

    private static final String INSERT_QUESTION_TIRAGE_QUERY = "INSERT INTO QUESTION_TIRAGE(estMarquee, numOrdreTheme, IdEpreuve, idQuestion) VALUES (?, ?, ?, ?)";
    private static final String DELETE_QUESTION_TIRAGE_QUERY = "DELETE FROM QUESTION_TIRAGE WHERE idQuestionTirage = ?";
    private static final String UPDATE_QUESTION_TIRAGE_QUERY = "UPDATE QUESTION_TIRAGE SET estMarquee = ?, numOrdre = ?, idEpreuve = ?, idQuestion = ? WHERE idQuestionTirage = ?";
    
    private ThemeDAO themeDAO = DAOFactory.themeDAO();
    private TestDAO testDAO = DAOFactory.testDAO();

    private static QuestionTirageDAOImpl instance;
    
    private QuestionTirageDAOImpl() {
        
    }
    
    public static QuestionTirageDAOImpl getInstance() {
        if(instance == null) {
            instance = new QuestionTirageDAOImpl();
        }
        return instance;
    }
	
	@Override
	public QuestionTirage insert(QuestionTirage questionTirage) throws DaoException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    try {
	    	connection = MSSQLConnectionFactory.get();
	            
	        statement = connection.prepareStatement(INSERT_QUESTION_TIRAGE_QUERY, Statement.RETURN_GENERATED_KEYS);
	            
	        statement.setBoolean(1,  questionTirage.getEstMarque());
	        statement.setInt(2,  questionTirage.getNumOrdre());
	        statement.setInt(3,  questionTirage.getEpreuve().getIdEpreuve());
	        statement.setInt(4,  questionTirage.getQuestion().getIdQuestion());

	        if (statement.executeUpdate() == 1) {
	            resultSet = statement.getGeneratedKeys();
	        }
	    } catch(SQLException e) {
	        throw new DaoException(e.getMessage(), e);
	    } finally {
	    	ResourceUtil.safeClose(resultSet, statement, connection);
	    }
	        
	    return questionTirage;
	}

	@Override
	public void update(QuestionTirage questionTirage) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(UPDATE_QUESTION_TIRAGE_QUERY);
            
            statement.setBoolean(1,  questionTirage.getEstMarque());
            statement.setInt(2,  questionTirage.getNumOrdre());
            statement.setInt(3,  questionTirage.getEpreuve().getIdEpreuve());
            statement.setInt(4,  questionTirage.getQuestion().getIdQuestion());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
	}

	@Override
	public void delete(Integer id) throws DaoException {
		
	}

	@Override
	public QuestionTirage selectById(Integer id) throws DaoException {
		return null;
	}

	@Override
	public List<QuestionTirage> selectAll() throws DaoException {
		return null;
	}

}
