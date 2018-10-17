package fr.eni.tp.qcm.bll.manager;

import java.util.List;

import fr.eni.tp.qcm.bo.QuestionTirage;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;

public interface QuestionTirageManager {

	List<QuestionTirage> findAll() throws ManagerException;
    
	QuestionTirage findOne(Integer id) throws ManagerException, ElementNotFoundException;
    
	List<QuestionTirage> findAllByEpreuve(Integer id) throws ManagerException, ElementNotFoundException;

    void deleteOne(Integer id) throws ManagerException;

    QuestionTirage saveOne(QuestionTirage questionTirage) throws ManagerException, FunctionalException;

	void updateQuestionTirage(QuestionTirage questionTirage) throws ManagerException, FunctionalException, DaoException;

	QuestionTirage findOneEpreuveQuestion(Integer epreuveId, Integer questionId) throws ElementNotFoundException, ManagerException;

}
