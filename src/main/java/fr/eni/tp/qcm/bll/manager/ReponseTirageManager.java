package fr.eni.tp.qcm.bll.manager;

import java.util.List;

import fr.eni.tp.qcm.bo.QuestionTirage;
import fr.eni.tp.qcm.bo.ReponseTirage;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

public interface ReponseTirageManager {
    ReponseTirage saveOne(ReponseTirage reponseTirage) throws ManagerException, FunctionalException;

    void deleteOne(Integer idProposition, Integer idQuestion, Integer idEpreuve) throws ManagerException;

	List<ReponseTirage> findAllByQuestionAndEpreuve(Integer idQuestion, Integer idEpreuve) throws ManagerException;

	ReponseTirage findOneByQuestionAndEpreuveAndProposition(Integer integer, Integer integer2, Integer integer3) throws ManagerException;

    
}
