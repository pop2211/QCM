package fr.eni.tp.qcm.bll.manager;

import java.util.List;

import fr.eni.tp.qcm.bo.Proposition;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

public interface PropositionManager {
	
	List<Proposition> findAll() throws ManagerException;
    
	Proposition findOne(Integer id) throws ManagerException, ElementNotFoundException;
	
	List<Proposition> findByQuestion(Integer idQuestion) throws ManagerException, ElementNotFoundException;
    
    void deleteOne(Integer id) throws ManagerException;
    
    Proposition saveOne(Proposition note) throws ManagerException, FunctionalException;

}
