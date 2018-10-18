package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.qcm.dal.dao.EpreuveDAO;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.qcm.utils.Result;
import fr.eni.tp.web.common.EniConstants;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.DateUtil;
import fr.eni.tp.web.common.util.ResourceUtil;

public class EpreuveDAOImpl implements EpreuveDAO{
	
	private static final String SELECT_ALL_EPREUVES_QUERY = "SELECT * FROM EPREUVE e INNER JOIN TEST t ON e.idTest = t.idTest INNER JOIN UTILISATEUR u ON u.idUtilisateur = e.idUtilisateur";
	private static final String SELECT_ALL_EPREUVES_UTILISATEUR_QUERY = "SELECT * FROM EPREUVE e INNER JOIN TEST t ON e.idTest = t.idTest INNER JOIN UTILISATEUR u ON u.idUtilisateur = e.idUtilisateur where e.idUtilisateur = ? AND e.etat IN ('EA', 'EC')";
    private static final String SELECT_ONE_EPREUVE_QUERY = "SELECT * FROM EPREUVE e INNER JOIN TEST t ON e.idTest = t.idTest INNER JOIN UTILISATEUR u ON u.idUtilisateur = e.idUtilisateur where idEpreuve = ?";
    private static final String SELECT_BY_UTIL_AND_STATUT = "SELECT * FROM EPREUVE e INNER JOIN TEST t ON e.idTest = t.idTest INNER JOIN UTILISATEUR u ON u.idUtilisateur = e.idUtilisateur WHERE e.idUtilisateur = ? AND etat = ?";
    private static final String SELECT_EPREUVE_BY_TEST_UTILISATEUR_QUERY = "SELECT * FROM EPREUVE e INNER JOIN TEST t ON e.idTest = t.idTest INNER JOIN UTILISATEUR u ON u.idUtilisateur = e.idUtilisateur where t.idTest = ? AND u.idUtilisateur = ?";
    private static final String INSERT_EPREUVE_QUERY = "INSERT INTO EPREUVE(dateDebutValidite, dateFinValidite, tempsEcoule, etat, noteObtenue, niveauObtenu, idTest, idUtilisateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_EPREUVE_QUERY = "DELETE FROM EPREUVE WHERE idEpreuve = ?";
    private static final String UPDATE_EPREUVE_QUERY = "UPDATE EPREUVE SET dateDebutValidite = ?, dateFinValidite = ?, tempsEcoule = ?, etat = ?, noteObtenue = ?, niveauObtenu = ?, idTest = ?, idUtilisateur = ? WHERE idEpreuve = ?";
    private static final String GET_RESULT = "EXEC GetResult @Epreuve = ?"; 
    
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
	            
	        statement.setTimestamp(1, Timestamp.valueOf(epreuve.getDateDebutValidite()));
	        statement.setTimestamp(2,  Timestamp.valueOf(epreuve.getDateFinValidite()));
	        statement.setTime(3, epreuve.getTempsEcoule());
	        statement.setString(4, epreuve.getEtat());
	        statement.setFloat(5, epreuve.getNoteObtenue());
	        statement.setString(6, epreuve.getNiveauObtenu());
	        statement.setInt(7, epreuve.getTest().getIdTest());
	        statement.setInt(8, epreuve.getUtilisateur().getIdUtilisateur());

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
            
            statement.setString(1,  epreuve.getDateDebutValidite());
	        statement.setString(2, epreuve.getDateFinValidite());
	        statement.setTime(3, epreuve.getTempsEcoule());
	        statement.setString(4, epreuve.getEtat());
	        statement.setFloat(5, epreuve.getNoteObtenue());
	        statement.setString(6, epreuve.getNiveauObtenu());
	        statement.setInt(7, epreuve.getTest().getIdTest());        
	        statement.setInt(8, epreuve.getUtilisateur().getIdUtilisateur());
	        statement.setInt(9, epreuve.getIdEpreuve());

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
	public Epreuve selectByIdTestIdUtilisateur(Integer idTest, Integer idUtilisateur) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Epreuve epreuve = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_EPREUVE_BY_TEST_UTILISATEUR_QUERY);
            
            statement.setInt(1, idTest);
            statement.setInt(2, idUtilisateur);
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
	public List<Epreuve> selectByUtilisateur(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Epreuve> epreuves = new ArrayList<>();
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_ALL_EPREUVES_UTILISATEUR_QUERY);
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

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
	
	@Override
	public List<Epreuve> selectByUtilAndStatut(Integer idUtil, String statut) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Epreuve> epreuves = new ArrayList<>();
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_BY_UTIL_AND_STATUT);
            
            statement.setInt(1, idUtil);
            statement.setString(2, statut);
            resultSet = statement.executeQuery();

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
	
	@Override
	public Epreuve resultSetToEpreuve(ResultSet resultSet) throws SQLException {
        
		Epreuve epreuve = new Epreuve();
		epreuve.setIdEpreuve(resultSet.getInt("idEpreuve"));
		epreuve.setDateDebutValidite(DateUtil.format(new Date(resultSet.getTimestamp("dateDebutValidite").getTime()), EniConstants.OUTPUT_DATE_TIME_FORMATTER));
		epreuve.setDateFinValidite(DateUtil.format(new Date(resultSet.getTimestamp("dateFinValidite").getTime()), EniConstants.OUTPUT_DATE_TIME_FORMATTER));
		epreuve.setTempsEcoule(resultSet.getTime("tempsEcoule"));
		epreuve.setEtat(resultSet.getString("etat"));
		epreuve.setNoteObtenue(resultSet.getFloat("noteObtenue"));
		epreuve.setNiveauObtenu(resultSet.getString("niveauObtenu"));
		epreuve.setTest(testDAO.resultSetToTest(resultSet));		
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setIdUtilisateur(resultSet.getInt("idUtilisateur"));
		utilisateur.setNomUtilisateur(resultSet.getString("nomUtilisateur"));
		utilisateur.setPrenomUtilisateur(resultSet.getString("prenomUtilisateur"));
		utilisateur.setEmail(resultSet.getString("email"));
		utilisateur.setPassword(resultSet.getString("password"));
		
		epreuve.setUtilisateur(utilisateur);

        return epreuve;
        
    }
	
	public Result GetResult(Integer idEpreuve) throws DaoException {
		Connection connection = null;
		CallableStatement statement = null;
        ResultSet resultSet = null;
        HashMap<Integer, Float> hm = new HashMap<>();
        Result result = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareCall(GET_RESULT);  
            statement.setInt(1, idEpreuve);  
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	hm.put(resultSet.getInt("idQuestion"), resultSet.getFloat("pts"));
            }
            result = new Result(hm);
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return result;
	}


}
