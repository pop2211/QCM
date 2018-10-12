package fr.eni.tp.qcm.dal.dao;

import java.util.List;

import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface EpreuveDAO extends GenericDAO<Epreuve, Integer>{
	
	List<Epreuve> selectByUtilisateur(Integer id) throws DaoException;

}
