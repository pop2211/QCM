package fr.eni.tp.qcm.bll.manager.impl;

import java.util.List;

import fr.eni.tp.qcm.bll.manager.UtilisateurManager;
import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.qcm.dal.dao.UtilisateurDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class UtilisateurManagerImpl implements UtilisateurManager {

	private UtilisateurDAO utilDAO = DAOFactory.utilDAO();
	    
    private static UtilisateurManagerImpl instance;
    
    private UtilisateurManagerImpl() {
        
    }
    
    public static UtilisateurManagerImpl getInstance() {
        if(instance == null) {
            instance = new UtilisateurManagerImpl();
        }
        return instance;
    }
	   
	@Override
	public Utilisateur Connexion(String email, String password) throws ManagerException {
		
		Utilisateur util = null;
		
		ValidationUtil.checkNotBlank(email);
		ValidationUtil.checkNotBlank(password);
		
		try {
			util = utilDAO.connexion(email, password);
		} catch (DaoException e) {
			throw new ManagerException(e.getMessage(), e);
		}
		
		return util;
	}

	@Override
	public List<Utilisateur> findAll() throws ManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur findOne(Integer id) throws ManagerException, ElementNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOne(Integer id) throws ManagerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur saveOne(Utilisateur util) throws ManagerException, FunctionalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> findAllCandidat() throws ManagerException {
		List<Utilisateur> utilisateurs = null;    
        try {
        	utilisateurs = utilDAO.selectAllCandidat();
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        }           
        return utilisateurs;
	}

}
