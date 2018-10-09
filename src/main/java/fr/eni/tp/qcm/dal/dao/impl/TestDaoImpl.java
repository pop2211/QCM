package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.bo.Theme;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class TestDaoImpl implements TestDAO {


    private static final String SELECT_TEST_QUERY = "SELECT idTest, libelle, description, duree, seuil_haut, seuil_bas FROM TEST WHERE idTest = ?";
    private static final String INSERT_TEST_QUERY = "INSERT INTO TEST(libelle, description, duree, seuil_haut, seuil_bas) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_TEST_QUERY = "DELETE FROM TEST WHERE idTest = ?";
    private static final String UPDATE_TEST_QUERY = "UPDATE TEST SET libelle = ?, descripion = ?, duree = ?, seuil_haut = ?, seuil_bas = ? WHERE idTest = ?";
    
    private static TestDaoImpl instance;
    
    private TestDaoImpl() {
        
    }
    
    public static TestDaoImpl getInstance() {
        if(instance == null) {
            instance = new TestDaoImpl();
        }
        return instance;
    }
    
	@Override
	public Test insert(Test test) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(INSERT_TEST_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, test.getLibelleTest());
            statement.setString(2, test.getDescription());
            statement.setInt(3, test.getDuree());
            statement.setInt(4, test.getSeuilBas());
            statement.setInt(5, test.getSeuilHaut());


            if (statement.executeUpdate() == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {            
                	test.setIdTest(resultSet.getInt(1));                    
                }
            }

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return test;
	}

	@Override
	public void update(Test test) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(UPDATE_TEST_QUERY);
            
            statement.setString(1, test.getLibelleTest());
            statement.setString(2, test.getDescription());
            statement.setInt(3, test.getDuree());
            statement.setInt(4, test.getSeuilHaut());
            statement.setInt(5, test.getSeuilBas());
            
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
            
            statement = connection.prepareStatement(DELETE_TEST_QUERY);
            
            statement.setInt(1, id);
            
            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
	}

	@Override
	public Test selectById(Integer id) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Test test = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_TEST_QUERY);
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	test = resultSetToTest(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return test;
	}

	@Override
	public List<Test> selectAll() throws DaoException {
		return null;
	}

	
    private Test resultSetToTest(ResultSet resultSet) throws SQLException {
        
        Test test = new Test();
        test.setIdTest(resultSet.getInt("idTheme"));
        test.setLibelleTest(resultSet.getString("libelle"));
        test.setDescription(resultSet.getString("description"));
        test.setDuree(resultSet.getInt("duree"));
        test.setSeuilHaut(resultSet.getInt("seuil_haut"));
        test.setSeuilBas(resultSet.getInt("seuil_bas"));

        return test;
        
    }
}
