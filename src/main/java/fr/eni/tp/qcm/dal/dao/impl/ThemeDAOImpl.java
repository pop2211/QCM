package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.bo.Theme;
import fr.eni.tp.qcm.dal.dao.ThemeDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class ThemeDAOImpl implements ThemeDAO {

    private static final String SELECT_THEME_QUERY = "SELECT idTheme, libelleTheme FROM THEME WHERE idTheme = ?";
    private static final String INSERT_THEME_QUERY = "INSERT INTO THEME(libelleTheme) VALUES (?)";
    private static final String DELETE_THEME_QUERY = "DELETE FROM THEME WHERE idTheme = ?";
    private static final String UPDATE_THEME_QUERY = "UPDATE THEME SET libelleTheme = ? WHERE idTest = ?";
    
    private static ThemeDAOImpl instance;
    
    private ThemeDAOImpl() {
        
    }
    
    public static ThemeDAOImpl getInstance() {
        if(instance == null) {
            instance = new ThemeDAOImpl();
        }
        return instance;
    }
	
	@Override
	public Theme insert(Theme theme) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(INSERT_THEME_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, theme.getLibelleTheme());

            if (statement.executeUpdate() == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {            
                	theme.setIdTheme(resultSet.getInt(1));                    
                }
            }

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return theme;
	}

	@Override
	public void update(Theme theme) throws DaoException {
		   
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(UPDATE_THEME_QUERY);
            
            statement.setString(1, theme.getLibelleTheme());
            
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
            
            statement = connection.prepareStatement(DELETE_THEME_QUERY);
            
            statement.setInt(1, id);
            
            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
	}
	
	

	@Override
	public Theme selectById(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Theme theme = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_THEME_QUERY);
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	theme = resultSetToTheme(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return theme;
	}

	@Override
	public List<Theme> selectAll() throws DaoException {
		return null;
	}

    @Override
    public Theme resultSetToTheme(ResultSet resultSet) throws SQLException {
        
        Theme theme = new Theme();
        theme.setIdTheme(resultSet.getInt("idTheme"));
        theme.setLibelleTheme(resultSet.getString("libelleTheme"));
        return theme;
        
    }

}
