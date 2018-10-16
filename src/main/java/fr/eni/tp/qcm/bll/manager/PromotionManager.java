package fr.eni.tp.qcm.bll.manager;

import java.util.List;

import fr.eni.tp.qcm.bo.Promotion;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public interface PromotionManager {
	
	List<Promotion> findAll() throws ManagerException;
	
	Promotion findOne(Integer id) throws ManagerException, ElementNotFoundException;

}
