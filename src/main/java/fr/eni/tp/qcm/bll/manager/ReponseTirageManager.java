package fr.eni.tp.qcm.bll.manager;

import fr.eni.tp.qcm.bo.ReponseTirage;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

public interface ReponseTirageManager {
    ReponseTirage saveOne(ReponseTirage reponseTirage) throws ManagerException, FunctionalException;
}
