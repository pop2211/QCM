package fr.eni.tp.qcm.bll.manager.impl;

import java.util.List;

import fr.eni.tp.qcm.bll.manager.QuestionTirageManager;
import fr.eni.tp.qcm.bo.QuestionTirage;
import fr.eni.tp.qcm.bo.SectionTest;
import fr.eni.tp.qcm.dal.dao.QuestionTirageDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class QuestionTirageManagerImpl implements QuestionTirageManager{

	private QuestionTirageDAO questionTirageDAO = DAOFactory.questionTirageDAO();
    
    private static QuestionTirageManagerImpl instance;
    
    private QuestionTirageManagerImpl() {
        
    }
    
    public static QuestionTirageManagerImpl getInstance() {
        if(instance == null) {
            instance = new QuestionTirageManagerImpl();
        }
        return instance;
    }
    
	@Override
	public List<QuestionTirage> findAll() throws ManagerException {
		return null;
	}

	@Override
	public QuestionTirage findOne(Integer id) throws ManagerException, ElementNotFoundException {
		return null;
	}

	@Override
	public void deleteOne(Integer id) throws ManagerException {
		try {
            ValidationUtil.checkNotNull(id);
            questionTirageDAO.delete(id);
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas être null", e);
        }
	}

	@Override
	public QuestionTirage saveOne(QuestionTirage questionTirage) throws ManagerException, FunctionalException {
		try {
            ValidationUtil.checkNotNull(questionTirage);
//            if(questionTirage.getEpreuve().getIdEpreuve() != null && questionTirage.getQuestion().getIdQuestion() != null) {
//            	System.out.println("update");
//            	questionTirageDAO.update(questionTirage);
//            } else {
            	System.out.println("insert");
            	questionTirageDAO.insert(questionTirage);
            //}
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);

        } catch (IllegalArgumentException e) {
            throw new ManagerException("La question n'est pas valide", e);
        }
        return questionTirage;
	}

	@Override
	public List<QuestionTirage> findAllByEpreuve(Integer id) throws ManagerException, ElementNotFoundException {
		List<QuestionTirage> questionTirage = null;    
        try {
        	questionTirage =  questionTirageDAO.selectByIdEpreuve(id);
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        }           
        return questionTirage;	
	}

}
