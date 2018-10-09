package fr.eni.tp.qcm.bll.manager;

import java.util.List;

import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

public interface QuestionManager {
	
	List<Question> findAll() throws ManagerException;
    
	Question findOne(Integer id) throws ManagerException, ElementNotFoundException;
    
    void deleteOne(Integer id) throws ManagerException;
    
    Question saveOne(Question question) throws ManagerException, FunctionalException;

}
