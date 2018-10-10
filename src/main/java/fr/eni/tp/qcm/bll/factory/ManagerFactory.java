package fr.eni.tp.qcm.bll.factory;

import fr.eni.tp.qcm.bll.manager.PropositionManager;
import fr.eni.tp.qcm.bll.manager.QuestionManager;
import fr.eni.tp.qcm.bll.manager.UtilisateurManager;
import fr.eni.tp.qcm.bll.manager.impl.PropositionManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.QuestionManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.UtilisateurManagerImpl;

public class ManagerFactory {
	
	public static QuestionManager questionManager() {
        return QuestionManagerImpl.getInstance();
    }
	
	public static PropositionManager propositionManager() {
        return PropositionManagerImpl.getInstance();
    }
	
	public static UtilisateurManager utilisateurManager() {
        return UtilisateurManagerImpl.getInstance();
    }
	
}
