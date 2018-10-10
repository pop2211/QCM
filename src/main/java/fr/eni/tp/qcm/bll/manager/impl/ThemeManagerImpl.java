package fr.eni.tp.qcm.bll.manager.impl;

import java.util.List;

import fr.eni.tp.qcm.bll.manager.ThemeManager;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.bo.Theme;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.qcm.dal.dao.ThemeDAO;
import fr.eni.tp.qcm.dal.factory.DAOFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class ThemeManagerImpl implements ThemeManager {

private ThemeDAO themeDAO = DAOFactory.themeDAO();
    
    private static ThemeManagerImpl instance;
    
    private ThemeManagerImpl() {
        
    }
    
    public static ThemeManagerImpl getInstance() {
        if(instance == null) {
            instance = new ThemeManagerImpl();
        }
        return instance;
    }

	@Override
	public List<Theme> findAll() throws ManagerException {
		List<Theme> themes = null;    
        try {
        	themes = themeDAO.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        }           
        return themes;
	}

	@Override
	public Theme findOne(Integer id) throws ManagerException, ElementNotFoundException {
		Theme theme = null;    
        try {
            ValidationUtil.checkNotNull(id);
            theme = themeDAO.selectById(id);
            if(theme == null) {
                throw new ElementNotFoundException("Le theme n'existe pas", null);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas être null", e);
        }
        return theme;	
	}

	@Override
	public void deleteOne(Integer id) throws ManagerException {
		try {
            ValidationUtil.checkNotNull(id);
            themeDAO.delete(id);
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("L'id ne peut pas être null", e);
        }
	}

	@Override
	public Theme saveOne(Theme theme) throws ManagerException, FunctionalException {
		try {
            ValidationUtil.checkNotNull(theme);
            ValidationUtil.checkNotBlank(theme.getLibelleTheme());

            if(theme.getIdTheme() != null) {
            	themeDAO.update(theme);
            } else {
            	themeDAO.insert(theme);
            }
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);

        } catch (IllegalArgumentException e) {
            throw new ManagerException("Le theme n'est pas valide", e);
        }
        return theme;
	}
}
