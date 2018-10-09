package fr.eni.tp.qcm.dal.factory;

import fr.eni.tp.qcm.dal.dao.UtilisateurDAO;
import fr.eni.tp.qcm.dal.dao.impl.UtilisateurDaoImpl;

public class DAOFactory {

    public static UtilisateurDAO utilDAO() {
        return UtilisateurDaoImpl.getInstance();
    }
}
