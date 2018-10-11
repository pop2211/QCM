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
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class TestDAOImpl implements TestDAO {

	private static final String SELECT_ALL_TEST_QUERY = "SELECT idTest, libelleTest, description, duree, seuilHaut, seuilBas FROM TEST";
    private static final String SELECT_TEST_QUERY = "SELECT idTest, libelleTest, description, duree, seuilHaut, seuilBas FROM TEST WHERE idTest = ?";
    private static final String INSERT_TEST_QUERY = "INSERT INTO TEST(libelleTest, description, duree, seuilHaut, seuilBas) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_TEST_QUERY = "DELETE FROM TEST WHERE idTest = ?";
    private static final String UPDATE_TEST_QUERY = "UPDATE TEST SET libelleTest = ?, descripion = ?, duree = ?, seuilHaut = ?, seuilBas = ? WHERE idTest = ?";
    
    private static TestDAOImpl instance;
    
    private TestDAOImpl() {
        
    }
    
    public static TestDAOImpl getInstance() {
        if(instance == null) {
            instance = new TestDAOImpl();
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
            statement.setTime(3, test.getDuree());
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
            statement.setTime(3, test.getDuree());
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
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Test> tests = new ArrayList<>();
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_TEST_QUERY);

            while (resultSet.next()) {
            	tests.add(resultSetToTest(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return tests;
	}

	@Override
    public Test resultSetToTest(ResultSet resultSet) throws SQLException {
        
        Test test = new Test();
        test.setIdTest(resultSet.getInt("idTest"));
        test.setLibelleTest(resultSet.getString("libelleTest"));
        test.setDescription(resultSet.getString("description"));
        test.setDuree(resultSet.getTime("duree"));
        test.setSeuilHaut(resultSet.getInt("seuilHaut"));
        test.setSeuilBas(resultSet.getInt("seuilBas"));

        return test;
        
    }
}
