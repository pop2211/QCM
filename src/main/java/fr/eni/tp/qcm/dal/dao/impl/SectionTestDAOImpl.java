package fr.eni.tp.qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.tp.qcm.bo.Proposition;
import fr.eni.tp.qcm.bo.SectionTest;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.dal.dao.QuestionDAO;
import fr.eni.tp.qcm.dal.dao.SectionTestDAO;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.qcm.dal.dao.ThemeDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;
import fr.eni.tp.web.common.util.ValidationUtil;

public class SectionTestDAOImpl implements SectionTestDAO{


    private static final String SELECT_SECTION_TEST_BY_TEST_QUERY = "SELECT idTest, idTheme, nbQuestionsATrier FROM SECTION_TEST WHERE idTest = ?";
    private static final String INSERT_SECTION_TEST_QUERY = "INSERT INTO SECTION_TEST(idTest, idTheme, nbQuestionsATrier) VALUES (?, ?, ?)";
    private static final String DELETE_SECTION_TEST_QUERY = "DELETE FROM SECTION_TEST WHERE idTest = ?";
    private static final String UPDATE_SECTION_TEST_QUERY = "UPDATE SECTION_TEST SET idTheme = ?, nbQuestionsATrier = ? WHERE idTest = ?";
    
    private ThemeDAO themeDAO = DAOFactory.themeDAO();
    private TestDAO testDAO = DAOFactory.testDAO();

    private static SectionTestDAOImpl instance;
    
    private SectionTestDAOImpl() {
        
    }
    
    public static SectionTestDAOImpl getInstance() {
        if(instance == null) {
            instance = new SectionTestDAOImpl();
        }
        return instance;
    }

	@Override
	public SectionTest insert(SectionTest element) throws DaoException {
		return null;
	}

	@Override
	public void update(SectionTest element) throws DaoException {
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		
	}

	@Override
	public List<SectionTest> selectAll() throws DaoException {
		return null;
	}

	@Override
	public SectionTest selectById(Integer id) throws DaoException {
		return null;
	}
	
	@Override
	public SectionTest selectByIdTest(Integer idTest) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SectionTest sectionTest = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(SELECT_SECTION_TEST_BY_TEST_QUERY);
            
            statement.setInt(1, idTest);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	sectionTest = resultSetToSectionTest(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return sectionTest;
	}

	private SectionTest resultSetToSectionTest(ResultSet resultSet) throws SQLException {
        
		SectionTest sectionTest = new SectionTest();
		sectionTest.setNbQuestionsATrier(resultSet.getInt("nbQuestionsATrier"));
		sectionTest.setTheme(themeDAO.resultSetToTheme(resultSet));
		sectionTest.setTest(testDAO.resultSetToTest(resultSet));
		
        return sectionTest;
        
    }
    
}
