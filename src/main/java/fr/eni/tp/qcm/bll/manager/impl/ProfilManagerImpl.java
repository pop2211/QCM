package fr.eni.tp.qcm.bll.manager.impl;

import java.util.List;

import fr.eni.tp.qcm.bll.manager.ProfilManager;
import fr.eni.tp.qcm.bo.Profil;
import fr.eni.tp.qcm.bo.Promotion;
import fr.eni.tp.qcm.dal.dao.ProfilDAO;
import fr.eni.tp.qcm.dal.dao.PromotionDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class ProfilManagerImpl implements ProfilManager{
	
	private ProfilDAO profilDAO = DAOFactory.profilDAO();
    
    private static ProfilManagerImpl instance;
    
    private ProfilManagerImpl() {
        
    }
    
    public static ProfilManagerImpl getInstance() {
        if(instance == null) {
            instance = new ProfilManagerImpl();
        }
        return instance;
    }

	@Override
	public List<Profil> findAll() throws ManagerException {
		List<Profil> profils = null;    
        try {
        	profils = profilDAO.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        }           
        return profils;
	}

	@Override
	public Profil findOne(Integer id) throws ManagerException, ElementNotFoundException {
		Profil profil = null;    
        try {
            ValidationUtil.checkNotNull(id);
            profil = profilDAO.selectById(id);
            if(profil == null) {
                throw new ElementNotFoundException("Le profil n'existe pas", null);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas être null", e);
        }
        return profil;
	}


}
