package fr.eni.tp.qcm.bll.manager;

import java.util.List;

import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

public interface TestManager {
	
	List<Test> findAll() throws ManagerException;
    
	Test findOne(Integer id) throws ManagerException, ElementNotFoundException;
    
    void deleteOne(Integer id) throws ManagerException;
    
    Test saveOne(Test test) throws ManagerException, FunctionalException;

}
