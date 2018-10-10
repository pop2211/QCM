package fr.eni.tp.qcm.bll.manager.impl;

import java.util.List;

import fr.eni.tp.qcm.bll.manager.SectionTestManager;
import fr.eni.tp.qcm.bo.SectionTest;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.dal.dao.SectionTestDAO;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;

public class SectionTestManagerImpl implements SectionTestManager {

	private SectionTestDAO sectionTestDAO = DAOFactory.sectionTestDAO();
    
    private static SectionTestManagerImpl instance;
    
    private SectionTestManagerImpl() {
        
    }
    
    public static SectionTestManagerImpl getInstance() {
        if(instance == null) {
            instance = new SectionTestManagerImpl();
        }
        return instance;
    }
    
	@Override
	public List<SectionTest> findAll() throws ManagerException {
		return null;
	}

	@Override
	public SectionTest findOne(Integer id) throws ManagerException, ElementNotFoundException {
		return null;
	}

	@Override
	public void deleteOne(Integer id) throws ManagerException {
	}

	@Override
	public SectionTest saveOne(SectionTest sectionTest) throws ManagerException, FunctionalException {
		return null;
	}

	@Override
	public List<SectionTest> selectByIdTest(Integer id) throws ManagerException, ElementNotFoundException {
		List<SectionTest> sectionTests = null;    
        try {
        	sectionTests = sectionTestDAO.selectByIdTest(id);
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        }           
        return sectionTests;	}

}
