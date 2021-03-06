package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.media.sound.PortMixerProvider;

import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.bo.SectionTest;
import fr.eni.tp.qcm.bo.Theme;
import fr.eni.tp.qcm.dal.dao.QuestionDAO;
import fr.eni.tp.qcm.dal.dao.ThemeDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class QuestionDAOImpl implements QuestionDAO{
	
	private static final String SELECT_ALL_QUESTIONS_QUERY = "SELECT * FROM QUESTION INNER JOIN THEME ON QUESTION.idTheme = THEME.idTheme";
    private static final String SELECT_ONE_QUESTION_QUERY = "SELECT * FROM QUESTION INNER JOIN THEME ON QUESTION.idTheme = THEME.idTheme where idQuestion = ?";
    private static final String SELECT_QUESTION_BY_THEME_QUERY = "SELECT * FROM QUESTION INNER JOIN THEME ON QUESTION.idTheme = THEME.idTheme WHERE QUESTION.idTheme = ?";
    private static final String INSERT_QUESTION_QUERY = "INSERT INTO QUESTION(enonce, media, points, idTheme) VALUES (?, ?, ?, ?)";
    private static final String DELETE_QUESTION_QUERY = "DELETE FROM QUESTION WHERE idQuestion = ?";
    private static final String UPDATE_QUESTION_QUERY = "UPDATE QUESTION SET enonce = ?, media = ?, points = ?, idTheme = ? WHERE idQuestion = ?";

    private static QuestionDAOImpl instance;
    private ThemeDAO themeDAO = DAOFactory.themeDAO();

    private QuestionDAOImpl() {
        
    }
    
    public static QuestionDAOImpl getInstance() {
        if(instance == null) {
            instance = new QuestionDAOImpl();
        }
        return instance;
    }
    
	/* (non-Javadoc)
	 * @see fr.eni.tp.qcm.dal.dao.GenericDAO#insert(java.lang.Object)
	 * Permet d'ajouter une question
	 */
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

	/* (non-Javadoc)
	 * @see fr.eni.tp.qcm.dal.dao.GenericDAO#update(java.lang.Object)
	 * Permet de modifier une question
	 */
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

	/* (non-Javadoc)
	 * @see fr.eni.tp.qcm.dal.dao.GenericDAO#delete(java.lang.Object)
	 * Permet de supprimer une question � l'aide de son id
	 */
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

	/* (non-Javadoc)
	 * @see fr.eni.tp.qcm.dal.dao.GenericDAO#selectById(java.lang.Object)
	 * Permet de r�cup�rer une question � l'aide de son id
	 */
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

	/* (non-Javadoc)
	 * @see fr.eni.tp.qcm.dal.dao.GenericDAO#selectAll()
	 * Permet de r�cup�rer toutes les questions
	 */
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
	
	@Override
	public List<Question> selectByIdTheme(Integer idTest) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Question> questions = new ArrayList<>();

        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_QUESTION_BY_THEME_QUERY);
            
            statement.setInt(1, idTest);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	questions.add(resultSetToQuestion(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return questions;
	}

	/* (non-Javadoc)
	 * @see fr.eni.tp.qcm.dal.dao.QuestionDAO#resultSetToQuestion(java.sql.ResultSet)
	 * Permet la cr�ation du resulset de question
	 */
	@Override
	public Question resultSetToQuestion(ResultSet resultSet) throws SQLException {
        
		Question question = new Question();
		question.setIdQuestion(resultSet.getInt("idQuestion"));
		question.setEnonce(resultSet.getString("enonce"));
        question.setMedia(resultSet.getString("media"));
        question.setPoints(resultSet.getInt("points"));
        question.setTheme(new Theme(resultSet.getInt("idTheme"), resultSet.getString("libelleTheme")));
        
        return question;
        
    }
	
	@Override
	public Question resultSetToQuestionOut(ResultSet resultSet) throws SQLException {
        
		Question question = new Question();
		question.setIdQuestion(resultSet.getInt("idQuestion"));
		question.setEnonce(resultSet.getString("enonce"));
        question.setMedia(resultSet.getString("media"));
        question.setPoints(resultSet.getInt("points"));
        question.setTheme(themeDAO.resultSetToTheme(resultSet));

        return question;
        
    }

}
