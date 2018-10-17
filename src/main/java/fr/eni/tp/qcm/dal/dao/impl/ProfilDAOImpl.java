package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.qcm.bo.Profil;
import fr.eni.tp.qcm.dal.dao.ProfilDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class ProfilDAOImpl implements ProfilDAO{
	
	private static final String SELECT_ALL_PROFILS_QUERY = "SELECT * FROM PROFIL";
	private static final String SELECT_ONE_PROFIL_QUERY = "SELECT * FROM PROFIL where idProfil= ?";
	
	private static ProfilDAOImpl instance;
    
    private ProfilDAOImpl() {
        
    }
    
    public static ProfilDAOImpl getInstance() {
        if(instance == null) {
            instance = new ProfilDAOImpl();
        }
        return instance;
    }

	@Override
	public Profil insert(Profil element) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Profil element) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Profil selectById(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Profil profil = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_ONE_PROFIL_QUERY);
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	profil = resultSetToProfil(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return profil;
	}

	@Override
	public List<Profil> selectAll() throws DaoException {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Profil> profils = new ArrayList<>();
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_PROFILS_QUERY);

            while (resultSet.next()) {
            	profils.add(resultSetToProfil(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return profils;
	}
	
	private Profil resultSetToProfil(ResultSet resultSet) throws SQLException {
        
		Profil profil = new Profil();
		profil.setIdProfil(resultSet.getInt("idProfil"));
		profil.setLibelleProfil(resultSet.getString("libelleProfil"));

        return profil;
        
    }

}
