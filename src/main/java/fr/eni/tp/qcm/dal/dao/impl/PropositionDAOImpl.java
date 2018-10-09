package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.qcm.bo.Proposition;
import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.dal.dao.PropositionDAO;
import fr.eni.tp.qcm.dal.dao.QuestionDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class PropositionDAOImpl implements PropositionDAO{
	
	private static final String SELECT_ALL_PROPOSITION_QUERY = "SELECT idProposition, p.enonce, estBonne, idQuestion, t.enonce, media, points, idTheme, libelleTheme FROM PROPOSITION p INNER JOIN QUESTION q ON p.idProposition = q.idProposition INNER JOIN THEME t ON t.idTheme = q.idTheme";
    private static final String SELECT_ONE_PROPOSITION_QUERY = "SELECT idProposition, enonce, estBonne, idQuestion, t.enonce, media, points, idTheme, libelleTheme FROM PROPOSITION p INNER JOIN QUESTION q ON p.idProposition = q.idProposition INNER JOIN THEME t ON t.idTheme = q.idTheme where idProposition = ?";
    private static final String SELECT_PROPOSITION_BY_QUESTION_QUERY = "SELECT idProposition, enonce, estBonne , idQuestion, t.enonce, media, points, idTheme, libelleTheme FROM PROPOSITION p INNER JOIN QUESTION q ON p.idProposition = q.idProposition INNER JOIN THEME t ON t.idTheme = q.idTheme where idQuestion = ?";
    private static final String INSERT_PROPOSITION_QUERY = "INSERT INTO PROPOSITION(enonce, estBonne, idQuestion) VALUES (?, ?, ?)";
    private static final String DELETE_PROPOSITION_QUERY = "DELETE FROM PROPOSITION WHERE idProposition = ?";
    private static final String UPDATE_PROPOSITION_QUERY = "UPDATE PROPOSITION SET enonce = ?, estBonne = ?, idQuestion = ? WHERE idProposition = ?";

    private QuestionDAO questionDAO = DAOFactory.questionDAO();
    
    
    private static PropositionDAOImpl instance;
    
    private PropositionDAOImpl() {
        
    }
    
    public static PropositionDAOImpl getInstance() {
        if(instance == null) {
            instance = new PropositionDAOImpl();
        }
        return instance;
    }

	/* (non-Javadoc)
	 * @see fr.eni.tp.qcm.dal.dao.GenericDAO#insert(java.lang.Object)
	 * Permet d'ajouter une proposition
	 */
	@Override
	public Proposition insert(Proposition proposition) throws DaoException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    try {
	    	connection = MSSQLConnectionFactory.get();
	            
	        statement = connection.prepareStatement(INSERT_PROPOSITION_QUERY, Statement.RETURN_GENERATED_KEYS);
	            
	        statement.setString(1, proposition.getEnonce());
	        statement.setBoolean(2, proposition.isEstBonne());
	        statement.setInt(3, proposition.getQuestion().getIdQuestion());

	        if (statement.executeUpdate() == 1) {
	            resultSet = statement.getGeneratedKeys();
	            if (resultSet.next()) {
	            	proposition.setIdProposition(resultSet.getInt(1));                    
	            }
	        }
	    } catch(SQLException e) {
	        throw new DaoException(e.getMessage(), e);
	    } finally {
	    	ResourceUtil.safeClose(resultSet, statement, connection);
	    }
	        
	    return proposition;
	}

	/* (non-Javadoc)
	 * @see fr.eni.tp.qcm.dal.dao.GenericDAO#update(java.lang.Object)
	 * Permet de modifier une proposition
	 */
	@Override
	public void update(Proposition proposition) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(UPDATE_PROPOSITION_QUERY);
            
            statement.setString(1, proposition.getEnonce());
	        statement.setBoolean(2, proposition.isEstBonne());
	        statement.setInt(3, proposition.getQuestion().getIdQuestion());
            statement.setInt(4, proposition.getIdProposition());
            
            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
		
	}

	/* (non-Javadoc)
	 * @see fr.eni.tp.qcm.dal.dao.GenericDAO#delete(java.lang.Object)
	 * Permet de supprimer une proposition à l'aide de son id
	 */
	@Override
	public void delete(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(DELETE_PROPOSITION_QUERY);
            
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
	 * Permet de récuperer une proposition en fonction de son id
	 */
	@Override
	public Proposition selectById(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Proposition proposition = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_ONE_PROPOSITION_QUERY);
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	proposition = resultSetToProposition(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return proposition;
	}

	/* (non-Javadoc)
	 * Permet de récuperer toutes les propositions
	 */
	@Override
	public List<Proposition> selectAll() throws DaoException {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Proposition> list = new ArrayList<>();
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_PROPOSITION_QUERY);

            while (resultSet.next()) {
                list.add(resultSetToProposition(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return list;
	}
	
	/**
	 * Permet de récupérer une proposition en fonction d'une question
	 * @param idQuestion
	 * @return proposition
	 * @throws DaoException
	 */
	@Override
	public Proposition selectByIdQuestion(Integer idQuestion) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Proposition proposition = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_PROPOSITION_BY_QUESTION_QUERY);
            
            statement.setInt(1, idQuestion);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	proposition = resultSetToProposition(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return proposition;
	}
	
	/**
	 * Permet la création du resultset de proposition
	 * @param resultSet
	 * @return proposition
	 * @throws SQLException
	 */
	private Proposition resultSetToProposition(ResultSet resultSet) throws SQLException {
        
		Proposition proposition = new Proposition();
		proposition.setIdProposition(resultSet.getInt("idProposition"));
		proposition.setEnonce(resultSet.getString("enonce"));
		proposition.setEstBonne(resultSet.getBoolean("estBonne"));
		proposition.setQuestion(questionDAO.resultSetToQuestion(resultSet));
			       
        return proposition;
        
    }

}
