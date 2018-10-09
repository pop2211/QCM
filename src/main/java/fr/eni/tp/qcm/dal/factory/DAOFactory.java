package fr.eni.tp.qcm.dal.factory;

import fr.eni.tp.qcm.dal.dao.QuestionDAO;

public class DAOFactory {
	
	public static QuestionDAO questionDAO() {
        return QuestionDaoImpl.getInstance();
    }

}
