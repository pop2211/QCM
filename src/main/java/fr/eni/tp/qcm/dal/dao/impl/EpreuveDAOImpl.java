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

import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.bo.Theme;
import fr.eni.tp.qcm.dal.dao.EpreuveDAO;
import fr.eni.tp.qcm.dal.dao.QuestionDAO;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class EpreuveDAOImpl implements EpreuveDAO{
	
	private static final String SELECT_ALL_EPREUVES_QUERY = "SELECT idEpreuve, dateDebutValidite, dateFinValidite, tempsEcoule, etat, noteObtenue, niveauObtenu, idTest, libelle, description, duree, seuil_haut, seuil_bas FROM EPREUVE e INNER JOIN TEST t ON e.idTest = t.idTest";
    private static final String SELECT_ONE_EPREUVE_QUERY = "SELECT idEpreuve, dateDebutValidite, dateFinValidite, tempsEcoule, etat, noteObtenue, niveauObtenu, idTest, libelle, description, duree, seuil_haut, seuil_bas FROM EPREUVE e INNER JOIN TEST t ON e.idTest = t.idTest where idQuestion = ?";
    private static final String INSERT_EPREUVE_QUERY = "INSERT INTO EPREUVE(dateDebutValidite, dateFinValidite, tempsEcoule, etat, noteObtenue, niveauObtenu, idTest) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_EPREUVE_QUERY = "DELETE FROM EPREUVE WHERE idEpreuve = ?";
    private static final String UPDATE_EPREUVE_QUERY = "UPDATE EPREUVE SET dateDebutValidite = ?, dateFinValidite = ?, tempsEcoule = ?, etat = ?, noteObtenue = ?, niveauObtenu = ?, idTest = ? WHERE idEpreuve = ?";

    private TestDAO testDAO = DAOFactory.testDAO();
    
    private static EpreuveDAOImpl instance;
    
    private EpreuveDAOImpl() {
        
    }
    
    public static EpreuveDAOImpl getInstance() {
        if(instance == null) {
            instance = new EpreuveDAOImpl();
        }
        return instance;
    }
    
	@Override
	public Epreuve insert(Epreuve epreuve) throws DaoException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    try {
	    	connection = MSSQLConnectionFactory.get();
	            
	        statement = connection.prepareStatement(INSERT_EPREUVE_QUERY, Statement.RETURN_GENERATED_KEYS);
	            
	        statement.setTimestamp(1,  new Timestamp(epreuve.getDateDebutValidite().getTime()));
	        statement.setTimestamp(2,  new Timestamp(epreuve.getDateFinValidite().getTime()));
	        statement.setInt(3, epreuve.getTempsEcoule());
	        statement.setString(4, epreuve.getEtat());
	        statement.setInt(5, epreuve.getNoteObtenue());
	        statement.setInt(6, epreuve.getNiveauObtenu());
	        statement.setInt(7, epreuve.getTest().getIdTest());

	        if (statement.executeUpdate() == 1) {
	            resultSet = statement.getGeneratedKeys();
	            if (resultSet.next()) {
	            	epreuve.setIdEpreuve(resultSet.getInt(1));                    
	            }
	        }
	    } catch(SQLException e) {
	        throw new DaoException(e.getMessage(), e);
	    } finally {
	    	ResourceUtil.safeClose(resultSet, statement, connection);
	    }
	        
	    return epreuve;
	}

	@Override
	public void update(Epreuve epreuve) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(UPDATE_EPREUVE_QUERY);
            
            statement.setTimestamp(1,  new Timestamp(epreuve.getDateDebutValidite().getTime()));
	        statement.setTimestamp(2,  new Timestamp(epreuve.getDateFinValidite().getTime()));
	        statement.setInt(3, epreuve.getTempsEcoule());
	        statement.setString(4, epreuve.getEtat());
	        statement.setInt(5, epreuve.getNoteObtenue());
	        statement.setInt(6, epreuve.getNiveauObtenu());
	        statement.setInt(7, epreuve.getTest().getIdTest());
	        statement.setInt(8, epreuve.getIdEpreuve());
            
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
            
            statement = connection.prepareStatement(DELETE_EPREUVE_QUERY);
            
            statement.setInt(1, id);
            
            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
		
	}

	@Override
	public Epreuve selectById(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Epreuve epreuve = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_ONE_EPREUVE_QUERY);
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	epreuve = resultSetToEpreuve(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return epreuve;
	}

	@Override
	public List<Epreuve> selectAll() throws DaoException {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Epreuve> epreuves = new ArrayList<>();
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_EPREUVES_QUERY);

            while (resultSet.next()) {
            	epreuves.add(resultSetToEpreuve(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return epreuves;
	}
	
	private Epreuve resultSetToEpreuve(ResultSet resultSet) throws SQLException {
        
		Epreuve epreuve = new Epreuve();
		epreuve.setIdEpreuve(resultSet.getInt("idEpreuve"));
		epreuve.setDateDebutValidite(new Date(resultSet.getTimestamp("dateDebutValidite").getTime()));
		epreuve.setDateFinValidite(new Date(resultSet.getTimestamp("dateFinValidite").getTime()));
		epreuve.setTempsEcoule(resultSet.getInt("tempsEcoule"));
		epreuve.setEtat(resultSet.getString("etat"));
		epreuve.setNoteObtenue(resultSet.getInt("noteObtenue"));
		epreuve.setNiveauObtenu(resultSet.getInt("niveauObtenu"));
		epreuve.setTest(testDAO.resultSetToTest(resultSet));
        
        return epreuve;
        
    }

}
