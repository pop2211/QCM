package fr.eni.tp.qcm.bll.manager;

import java.util.List;

import fr.eni.tp.qcm.bo.Theme;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

public interface ThemeManager {

	List<Theme> findAll() throws ManagerException;
    
	Theme findOne(Integer id) throws ManagerException, ElementNotFoundException;
    
    void deleteOne(Integer id) throws ManagerException;
    
    Theme saveOne(Theme theme) throws ManagerException, FunctionalException;
}

