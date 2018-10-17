package fr.eni.tp.qcm.bll.manager;

import java.util.List;

import fr.eni.tp.qcm.bo.Profil;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface ProfilManager {
	
	List<Profil> findAll() throws ManagerException;
	
	Profil findOne(Integer id) throws ManagerException, ElementNotFoundException;

}
