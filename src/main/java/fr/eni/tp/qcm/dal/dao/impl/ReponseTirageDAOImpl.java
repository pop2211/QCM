package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.qcm.bo.QuestionTirage;
import fr.eni.tp.qcm.bo.ReponseTirage;
import fr.eni.tp.qcm.dal.dao.EpreuveDAO;
import fr.eni.tp.qcm.dal.dao.PropositionDAO;
import fr.eni.tp.qcm.dal.dao.QuestionDAO;
import fr.eni.tp.qcm.dal.dao.QuestionTirageDAO;
import fr.eni.tp.qcm.dal.dao.ReponseTirageDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class ReponseTirageDAOImpl implements ReponseTirageDAO{

    private static final String SELECT_REPONSE_TIRAGE_QUERY = "";
    private static final String SELECT_REPONSE_TIRAGE_QUERY_BY_EPREUVE_AND_QUESTION = "SELECT * FROM REPONSE_TIRAGE rt INNER JOIN QUESTION q ON rt.idQuestion = q.idQuestion INNER JOIN EPREUVE e ON rt.idEpreuve = e.idEpreuve  INNER JOIN PROPOSITION p ON rt.idProposition = p.idProposition WHERE idQuestion = ? AND idEpreuve = ?";

    private static final String INSERT_REPONSE_TIRAGE_QUERY = "INSERT INTO REPONSE_TIRAGE(idProposition, idQuestion, idEpreuve) VALUES(?, ?, ?)";
    private static final String DELETE_REPONSE_TIRAGE_QUERY = "DELETE FROM REPONSE_TIRAGE WHERE idProposition = ? AND idQuestion = ? AND idEpreuve = ?";
    
    private QuestionDAO questionDAO = DAOFactory.questionDAO();
    private PropositionDAO propositionDAO = DAOFactory.propositionDAO();
    private EpreuveDAO epreuveDAO = DAOFactory.epreuveDAO();

    private static ReponseTirageDAOImpl instance;
    
    private ReponseTirageDAOImpl() {
        
    }
    
    public static ReponseTirageDAOImpl getInstance() {
        if(instance == null) {
            instance = new ReponseTirageDAOImpl();
        }
        return instance;
    }
	
	@Override
	public ReponseTirage insert(ReponseTirage reponseTirage) throws DaoException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    try {
	    	connection = MSSQLConnectionFactory.get();
	            
	        statement = connection.prepareStatement(INSERT_REPONSE_TIRAGE_QUERY, Statement.RETURN_GENERATED_KEYS);
	            
	        statement.setInt(1,  reponseTirage.getProposition().getIdProposition());
	        statement.setInt(2,  reponseTirage.getQuestion().getIdQuestion());
	        statement.setInt(3,  reponseTirage.getEpreuve().getIdEpreuve());
	        
	        if (statement.executeUpdate() == 1) {
	            resultSet = statement.getGeneratedKeys();
	        }
	    } catch(SQLException e) {
	        throw new DaoException(e.getMessage(), e);
	    } finally {
	    	ResourceUtil.safeClose(resultSet, statement, connection);
	    }
	        
	    return reponseTirage;
	}

	@Override
	public void update(ReponseTirage element) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer idProposition, Integer idQuestion, Integer idEpreuve) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(DELETE_REPONSE_TIRAGE_QUERY);
            
            statement.setInt(1, idProposition);
            statement.setInt(2, idQuestion);
            statement.setInt(3, idEpreuve);

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
	}
	@Override
	public List<ReponseTirage> selectByQuestionAndEpreuve(Integer idQuestion, Integer idEpreuve) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ReponseTirage> reponseTirage = new ArrayList<>();

        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_REPONSE_TIRAGE_QUERY_BY_EPREUVE_AND_QUESTION);
            
            statement.setInt(1, idQuestion);
            statement.setInt(2, idEpreuve);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	reponseTirage.add(resultSetToReponseTirage(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return reponseTirage;
	}
	
	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReponseTirage selectById(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReponseTirage> selectAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ReponseTirage resultSetToReponseTirage(ResultSet resultSet) throws SQLException {

		ReponseTirage reponseTirage = new ReponseTirage();
		reponseTirage.setProposition(propositionDAO.resultSetToProposition(resultSet));
		reponseTirage.setQuestion(questionDAO.resultSetToQuestion(resultSet));
		reponseTirage.setEpreuve(epreuveDAO.resultSetToEpreuve(resultSet));

        return reponseTirage;
        
    }

}
