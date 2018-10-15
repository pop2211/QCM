package fr.eni.tp.qcm.bll.manager;

import java.util.List;

import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

public interface UtilisateurManager {
	Utilisateur Connexion(String email, String password) throws ManagerException;;
	
	List<Utilisateur> findAll() throws ManagerException;
	
	List<Utilisateur> findAllCandidat() throws ManagerException;
	
	List<Utilisateur> findByIdPromotion(Integer idPromotion) throws ManagerException;
    
	Utilisateur findOne(Integer id) throws ManagerException, ElementNotFoundException;
    
    void deleteOne(Integer id) throws ManagerException;
    
    Utilisateur saveOne(Utilisateur util) throws ManagerException, FunctionalException;
}
