package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.qcm.bo.Profil;
import fr.eni.tp.qcm.bo.Promotion;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.qcm.dal.dao.UtilisateurDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class UtilisateurDAOImpl implements UtilisateurDAO{
	
	private static final String INSERT_UTILISATEUR_QUERY = "INSERT INTO UTILISATEUR(nomUtilisateur, prenomUtilisateur, email, password, idProfil, idPromotion) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_UTILISATEUR_QUERY = "UPDATE UTILISATEUR SET nomUtilisateur = ?, prenomUtilisateur  = ?, email  = ?, password  = ?, idProfil  = ?, idPromotion  = ? WHERE idUtilisateur = ?";
	private static final String DELETE_UTILISATEUR_QUERY = "DELETE FROM UTILISATEUR WHERE idUtilisateur = ?";
	private static final String JOINTURE = "left join PROFIL on u.idProfil= PROFIL.idProfil left join PROMOTION on u.idPromotion = PROMOTION.idPromotion";
    private static final String SELECT_FOR_CONNEXION = "SELECT idUtilisateur, nomUtilisateur, prenomUtilisateur, email, password, PROFIL.idProfil, libelleProfil, PROMOTION.idPromotion, libellePromotion from UTILISATEUR u " + JOINTURE + " where email = ? and password = ?";
	private static final String SELECT_UTILISATEUR_BY_ID = "SELECT idUtilisateur, nomUtilisateur, prenomUtilisateur, email, password, PROFIL.idProfil, libelleProfil, PROMOTION.libellePromotion FROM UTILISATEUR u " + JOINTURE + " WHERE idUtilisateur = ?";
	private static final String SELECT_ALL_UTILISATEUR = "SELECT idUtilisateur, nomUtilisateur, prenomUtilisateur, email, password, PROFIL.idProfil, libelleProfil, PROMOTION.idPromotion, libellePromotion FROM UTILISATEUR u " + JOINTURE;
    
	private static UtilisateurDAOImpl instance;
	
	private UtilisateurDAOImpl() {
	        
	}
	    
    public static UtilisateurDAOImpl getInstance() {
        if(instance == null) {
            instance = new UtilisateurDAOImpl();
        }
        return instance;
    }
	    
	@Override
	public Utilisateur insert(Utilisateur util) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(INSERT_UTILISATEUR_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, util.getNomUtilisateur());
            statement.setString(2, util.getPrenomUtilisateur());
            statement.setString(3, util.getEmail());
            statement.setString(4, util.getPassword());
            statement.setInt(5, util.getProfil().getIdProfil());
            statement.setInt(6, util.getPromotion().getIdPromotion());

            if (statement.executeUpdate() == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    
                    util.setIdUtilisateur(resultSet.getInt(1));                    
                }
            }

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return util;
	}

	@Override
	public void update(Utilisateur util) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(UPDATE_UTILISATEUR_QUERY);
            
            statement.setString(1, util.getNomUtilisateur());
            statement.setString(2, util.getPrenomUtilisateur());
            statement.setString(3, util.getEmail());
            statement.setString(4, util.getPassword());
            statement.setInt(5, util.getProfil().getIdProfil());
            statement.setInt(6, util.getPromotion().getIdPromotion());
            
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
            
            statement = connection.prepareStatement(DELETE_UTILISATEUR_QUERY);
            
            statement.setInt(1, id);
            
            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
	}

	@Override
	public Utilisateur selectById(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utilisateur util = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_UTILISATEUR_BY_ID);
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                util = resultSetToUtilisateur(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return util;
	}

	@Override
	public List<Utilisateur> selectAll() throws DaoException {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Utilisateur> list = new ArrayList<>();
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_UTILISATEUR);

            while (resultSet.next()) {
                list.add(resultSetToUtilisateur(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return list;
	}
	
	@Override
	public Utilisateur connexion(String email, String password) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utilisateur util = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_FOR_CONNEXION);
            
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                util = resultSetToUtilisateur(resultSet);
            }
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
		return util;
	}
	
	@Override
    public Utilisateur resultSetToUtilisateur(ResultSet resultSet) throws SQLException {
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setIdUtilisateur(resultSet.getInt("idUtilisateur"));
		utilisateur.setNomUtilisateur(resultSet.getString("nomUtilisateur"));
		utilisateur.setPrenomUtilisateur(resultSet.getString("prenomUtilisateur"));
		utilisateur.setEmail(resultSet.getString("email"));
		utilisateur.setPassword(resultSet.getString("password"));
        
		utilisateur.setProfil(new Profil(resultSet.getInt("idProfil"), resultSet.getString("libelleProfil")));
		utilisateur.setPromotion(new Promotion(resultSet.getInt("idPromotion"), resultSet.getString("libellePromotion")));
        
        return utilisateur;
        
    }
}
