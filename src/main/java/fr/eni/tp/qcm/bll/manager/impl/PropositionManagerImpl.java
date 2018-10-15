package fr.eni.tp.qcm.bll.manager.impl;

import java.util.List;

import fr.eni.tp.qcm.bll.manager.PropositionManager;
import fr.eni.tp.qcm.bo.Proposition;
import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.dal.dao.PropositionDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class PropositionManagerImpl implements PropositionManager{
	
	private PropositionDAO propositionDAO = DAOFactory.propositionDAO();
    
    private static PropositionManagerImpl instance;
    
    private PropositionManagerImpl() {
        
    }
    
    public static PropositionManagerImpl getInstance() {
        if(instance == null) {
            instance = new PropositionManagerImpl();
        }
        return instance;
    }

	@Override
	public List<Proposition> findAll() throws ManagerException {
		List<Proposition> propositions = null;    
        try {
        	propositions = propositionDAO.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        }           
        return propositions;
	}

	@Override
	public Proposition findOne(Integer id) throws ManagerException, ElementNotFoundException {
		Proposition proposition = null;    
        try {
            ValidationUtil.checkNotNull(id);
            proposition = propositionDAO.selectById(id);
            if(proposition == null) {
                throw new ElementNotFoundException("La proposition n'existe pas", null);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas �tre null", e);
        }
        return proposition;
	}

	@Override
	public void deleteOne(Integer id) throws ManagerException {
		try {
            ValidationUtil.checkNotNull(id);
            propositionDAO.delete(id);
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas �tre null", e);
        }
		
	}

	@Override
	public Proposition saveOne(Proposition proposition) throws ManagerException, FunctionalException {
		try {
            ValidationUtil.checkNotNull(proposition);
            ValidationUtil.checkNotBlank(proposition.getEnonce());
            ValidationUtil.checkNotNull(proposition.isEstBonne());
            if(proposition.getIdProposition() != null) {
            	propositionDAO.update(proposition);
            } else {
            	propositionDAO.insert(proposition);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);

        } catch (IllegalArgumentException e) {
            throw new ManagerException("La proposition n'est pas valide", e);
        }
        return proposition;
	}

	@Override
	public List<Proposition> findByQuestion(Integer idQuestion) throws ManagerException, ElementNotFoundException {
		List<Proposition> propositions = null;    
        try {
            ValidationUtil.checkNotNull(idQuestion);
            propositions = propositionDAO.selectByIdQuestion(idQuestion);
            if(propositions == null) {
                throw new ElementNotFoundException("La proposition n'existe pas", null);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas �tre null", e);
        }
        return propositions;
	}

}
