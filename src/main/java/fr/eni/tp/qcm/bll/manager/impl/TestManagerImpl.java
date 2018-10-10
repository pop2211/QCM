package fr.eni.tp.qcm.bll.manager.impl;

import java.util.List;

import fr.eni.tp.qcm.bll.manager.TestManager;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class TestManagerImpl implements TestManager {

	private TestDAO testDAO = DAOFactory.testDAO();
    
    private static TestManagerImpl instance;
    
    private TestManagerImpl() {
        
    }
    
    public static TestManagerImpl getInstance() {
        if(instance == null) {
            instance = new TestManagerImpl();
        }
        return instance;
    }
    
	@Override
	public List<Test> findAll() throws ManagerException {
		List<Test> tests = null;    
        try {
        	tests = testDAO.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        }           
        return tests;
    }

	@Override
	public Test findOne(Integer id) throws ManagerException, ElementNotFoundException {
		Test test = null;    
        try {
            ValidationUtil.checkNotNull(id);
            test = testDAO.selectById(id);
            if(test == null) {
                throw new ElementNotFoundException("Le test n'existe pas", null);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas être null", e);
        }
        return test;	
        }

	@Override
	public void deleteOne(Integer id) throws ManagerException {
		try {
            ValidationUtil.checkNotNull(id);
            testDAO.delete(id);
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas être null", e);
        }
	}

	@Override
	public Test saveOne(Test test) throws ManagerException, FunctionalException {
		try {
            ValidationUtil.checkNotNull(test);
            ValidationUtil.checkNotBlank(test.getDescription());
            ValidationUtil.checkNotNull(test.getLibelleTest());
            ValidationUtil.checkNotNull(test.getDuree());
            ValidationUtil.checkNotNull(test.getSeuilBas());
            ValidationUtil.checkNotNull(test.getSeuilHaut());

            if(test.getIdTest() != null) {
            	testDAO.update(test);
            } else {
                testDAO.insert(test);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);

        } catch (IllegalArgumentException e) {
            throw new ManagerException("Le test n'est pas valide", e);
        }
        return test;
	}

}
