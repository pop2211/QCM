package fr.eni.tp.qcm.bll.manager.impl;

import java.util.List;

import fr.eni.tp.qcm.bll.manager.QuestionManager;
import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.dal.dao.QuestionDAO;
import fr.eni.tp.qcm.dal.factory.DaoFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class QuestionManagerImpl implements QuestionManager{
	
	private QuestionDAO questionDAO = DaoFactory.questionDAO();
    
    private static QuestionManagerImpl instance;
    
    private QuestionManagerImpl() {
        
    }
    
    public static QuestionManagerImpl getInstance() {
        if(instance == null) {
            instance = new QuestionManagerImpl();
        }
        return instance;
    }

	@Override
	public List<Question> findAll() throws ManagerException {
		List<Question> questions = null;    
        try {
        	questions = questionDAO.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        }           
        return questions;
	}

	@Override
	public Question findOne(Integer id) throws ManagerException, ElementNotFoundException {
		Question question = null;    
        try {
            ValidationUtil.checkNotNull(id);
            question = questionDAO.selectById(id);
            if(question == null) {
                throw new ElementNotFoundException("La question n'existe pas", null);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas être null", e);
        }
        return question;
	}

	@Override
	public void deleteOne(Integer id) throws ManagerException {
		try {
            ValidationUtil.checkNotNull(id);
            questionDAO.delete(id);
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas être null", e);
        }
		
	}

	@Override
	public Question saveOne(Question question) throws ManagerException, FunctionalException {
		try {
            ValidationUtil.checkNotNull(question);
            ValidationUtil.checkNotBlank(question.getEnonce());
            ValidationUtil.checkNotNull(question.getPoints());
            if(question.getIdQuestion() != null) {
            	questionDAO.update(question);
            } else {
                questionDAO.insert(question);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);

        } catch (IllegalArgumentException e) {
            throw new ManagerException("La question n'est pas valide", e);
        }
        return question;
	}

}
