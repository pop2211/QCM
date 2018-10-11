package fr.eni.tp.qcm.dal.factory;

import fr.eni.tp.qcm.dal.dao.EpreuveDAO;
import fr.eni.tp.qcm.dal.dao.PropositionDAO;
import fr.eni.tp.qcm.dal.dao.QuestionDAO;
import fr.eni.tp.qcm.dal.dao.SectionTestDAO;
import fr.eni.tp.qcm.dal.dao.TestDAO;
import fr.eni.tp.qcm.dal.dao.ThemeDAO;
import fr.eni.tp.qcm.dal.dao.UtilisateurDAO;
import fr.eni.tp.qcm.dal.dao.impl.EpreuveDAOImpl;
import fr.eni.tp.qcm.dal.dao.impl.PropositionDAOImpl;
import fr.eni.tp.qcm.dal.dao.impl.QuestionDAOImpl;
import fr.eni.tp.qcm.dal.dao.impl.SectionTestDAOImpl;
import fr.eni.tp.qcm.dal.dao.impl.TestDAOImpl;
import fr.eni.tp.qcm.dal.dao.impl.ThemeDAOImpl;
import fr.eni.tp.qcm.dal.dao.impl.UtilisateurDAOImpl;

public class DAOFactory {

    public static UtilisateurDAO utilDAO() {
        return UtilisateurDAOImpl.getInstance();
    }
    
	public static QuestionDAO questionDAO() {
        return QuestionDAOImpl.getInstance();
    }
	
	public static PropositionDAO propositionDAO() {
        return PropositionDAOImpl.getInstance();
	}
	
	public static EpreuveDAO epreuveDAO() {
        return EpreuveDAOImpl.getInstance();
	}
	
	public static TestDAO testDAO() {
        return TestDAOImpl.getInstance();
	}

	public static ThemeDAO themeDAO() {
        return ThemeDAOImpl.getInstance();
	}

	public static SectionTestDAO sectionTestDAO() {
        return SectionTestDAOImpl.getInstance();

	}

	public static UtilisateurDAO utilisateurDAO() {
        return UtilisateurDAOImpl.getInstance();
	}
}
