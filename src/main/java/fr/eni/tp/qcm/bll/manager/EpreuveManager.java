package fr.eni.tp.qcm.bll.manager;

import java.util.List;

import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

public interface EpreuveManager {

	List<Epreuve> findAll() throws ManagerException;
    
	Epreuve findOne(Integer id) throws ManagerException, ElementNotFoundException;
	
	List<Epreuve> findAllByIdUtilisateur(Integer id) throws ManagerException, ElementNotFoundException;
    
    void deleteOne(Integer id) throws ManagerException;
    
    Epreuve saveOne(Epreuve epreuve) throws ManagerException, FunctionalException;
    
    List<Epreuve> findByUtilAndStatut(Integer idUtil, String statut) throws ManagerException, ElementNotFoundException;;
}
