package fr.eni.tp.qcm.bll.manager;

import java.util.List;

import fr.eni.tp.qcm.bo.SectionTest;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

public interface SectionTestManager {

	List<SectionTest> findAll() throws ManagerException;
    
	SectionTest findOne(Integer id) throws ManagerException, ElementNotFoundException;
    
	List<SectionTest> selectByIdTest(Integer id) throws ManagerException, ElementNotFoundException;

    void deleteOne(Integer id) throws ManagerException;
    
    SectionTest saveOne(SectionTest sectionTest) throws ManagerException, FunctionalException;

}
