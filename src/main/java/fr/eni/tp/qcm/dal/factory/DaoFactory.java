package fr.eni.tp.qcm.dal.factory;

import fr.eni.tp.qcm.dal.dao.PropositionDAO;
import fr.eni.tp.qcm.dal.dao.QuestionDAO;
import fr.eni.tp.qcm.dal.dao.impl.PropositionDAOImpl;
import fr.eni.tp.qcm.dal.dao.impl.QuestionDAOImpl;

public class DaoFactory {	
	
	public static QuestionDAO questionDAO() {
        return QuestionDAOImpl.getInstance();
    }
	
	public static PropositionDAO propositionDAO() {
        return PropositionDAOImpl.getInstance();
    }
}
