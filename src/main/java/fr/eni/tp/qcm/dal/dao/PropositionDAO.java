package fr.eni.tp.qcm.dal.dao;

import fr.eni.tp.qcm.bo.Proposition;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface PropositionDAO extends GenericDAO<Proposition, Integer>{
	
	Proposition selectByIdQuestion(Integer idQuestion) throws DaoException;

}