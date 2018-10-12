package fr.eni.tp.qcm.bll.manager.impl;

import java.util.List;

import fr.eni.tp.qcm.bll.manager.EpreuveManager;
import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.dal.dao.EpreuveDAO;
import fr.eni.tp.qcm.dal.dao.QuestionDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class EpreuveManagerImpl implements EpreuveManager{
	
	private EpreuveDAO epreuveDAO = DAOFactory.epreuveDAO();
    
    private static EpreuveManagerImpl instance;
    
    private EpreuveManagerImpl() {
        
    }
    
    public static EpreuveManagerImpl getInstance() {
        if(instance == null) {
            instance = new EpreuveManagerImpl();
        }
        return instance;
    }

	@Override
	public List<Epreuve> findAll() throws ManagerException {
		List<Epreuve> epreuves = null;    
        try {
        	epreuves = epreuveDAO.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        }           
        return epreuves;
	}

	@Override
	public Epreuve findOne(Integer id) throws ManagerException, ElementNotFoundException {
		Epreuve epreuve = null;    
        try {
            ValidationUtil.checkNotNull(id);
            epreuve = epreuveDAO.selectById(id);
            if(epreuve == null) {
                throw new ElementNotFoundException("L'épreuve n'existe pas", null);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas �tre null", e);
        }
        return epreuve;
	}

	@Override
	public void deleteOne(Integer id) throws ManagerException {
		try {
            ValidationUtil.checkNotNull(id);
            epreuveDAO.delete(id);
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas �tre null", e);
        }
		
	}

	@Override
	public Epreuve saveOne(Epreuve epreuve) throws ManagerException, FunctionalException {
		try {
            ValidationUtil.checkNotNull(epreuve);
            ValidationUtil.checkNotBlank(epreuve.getEtat());
            ValidationUtil.checkNotNull(epreuve.getDateDebutValidite());
            ValidationUtil.checkNotNull(epreuve.getDateFinValidite());
            if(epreuve.getIdEpreuve() != null) {
            	epreuveDAO.update(epreuve);
            } else {
            	epreuveDAO.insert(epreuve);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);

        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'épreuve n'est pas valide", e);
        }
        return epreuve;
	}

	@Override
	public List<Epreuve> findAllByIdUtilisateur(Integer id) throws ManagerException, ElementNotFoundException {
		List<Epreuve> epreuves = null;       
        try {
            ValidationUtil.checkNotNull(id);
            epreuves = epreuveDAO.selectByUtilisateur(id);
            if(epreuves == null) {
                throw new ElementNotFoundException("L'épreuve n'existe pas", null);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas être null", e);
        }
        return epreuves;
	}

}
