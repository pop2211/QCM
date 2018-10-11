package fr.eni.tp.qcm.dal.dao.impl;

import java.util.List;

import fr.eni.tp.qcm.bo.QuestionTirage;
import fr.eni.tp.qcm.dal.dao.QuestionTirageDAO;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.qcm.dal.dao.ThemeDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.dal.exception.DaoException;

public class QuestionTirageDAOImpl implements QuestionTirageDAO{


    private static final String SELECT_QUESTION_TIRAGE_QUERY = "SELECT * FROM SECTION_TEST INNER JOIN THEME ON SECTION_TEST.idTheme = THEME.idTheme WHERE idTest = ?";

    private static final String INSERT_QUESTION_TIRAGE_QUERY = "INSERT INTO QUESTION_TIRAGE(estMarquee, numOrdreTheme, idUtilisateur, IdEpreuve, idQuestion) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_QUESTION_TIRAGE_QUERY = "DELETE FROM QUESTION_TIRAGE WHERE idQuestionTirage = ?";
    private static final String UPDATE_QUESTION_TIRAGE_QUERY = "UPDATE QUESTION_TIRAGE SET estMarquee = ?, numOrdre = ? WHERE idTest = ?";
    
    private ThemeDAO themeDAO = DAOFactory.themeDAO();
    private TestDAO testDAO = DAOFactory.testDAO();

    private static QuestionTirageDAOImpl instance;
    
    private QuestionTirageDAOImpl() {
        
    }
    
    public static QuestionTirageDAOImpl getInstance() {
        if(instance == null) {
            instance = new QuestionTirageDAOImpl();
        }
        return instance;
    }
	
	@Override
	public QuestionTirage insert(QuestionTirage element) throws DaoException {
		return null;
	}

	@Override
	public void update(QuestionTirage element) throws DaoException {
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		
	}

	@Override
	public QuestionTirage selectById(Integer id) throws DaoException {
		return null;
	}

	@Override
	public List<QuestionTirage> selectAll() throws DaoException {
		return null;
	}

}
