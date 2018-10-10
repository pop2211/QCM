package fr.eni.tp.qcm.dal.dao;

import java.util.List;

import fr.eni.tp.qcm.bo.SectionTest;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface SectionTestDAO extends GenericDAO<SectionTest, Integer>{

	List<SectionTest> selectByIdTest(Integer idTest) throws DaoException;

}
