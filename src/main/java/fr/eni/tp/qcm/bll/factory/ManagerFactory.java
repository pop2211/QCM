package fr.eni.tp.qcm.bll.factory;

import fr.eni.tp.qcm.bll.manager.EpreuveManager;
import fr.eni.tp.qcm.bll.manager.ProfilManager;
import fr.eni.tp.qcm.bll.manager.PromotionManager;
import fr.eni.tp.qcm.bll.manager.PropositionManager;
import fr.eni.tp.qcm.bll.manager.QuestionManager;
import fr.eni.tp.qcm.bll.manager.QuestionTirageManager;
import fr.eni.tp.qcm.bll.manager.ReponseTirageManager;
import fr.eni.tp.qcm.bll.manager.SectionTestManager;
import fr.eni.tp.qcm.bll.manager.TestManager;
import fr.eni.tp.qcm.bll.manager.ThemeManager;
import fr.eni.tp.qcm.bll.manager.UtilisateurManager;
import fr.eni.tp.qcm.bll.manager.impl.EpreuveManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.ProfilManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.PromotionManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.PropositionManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.QuestionManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.QuestionTirageManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.ReponseTirageManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.SectionTestManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.TestManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.ThemeManagerImpl;
import fr.eni.tp.qcm.bll.manager.impl.UtilisateurManagerImpl;

public class ManagerFactory {
	
	public static QuestionManager questionManager() {
        return QuestionManagerImpl.getInstance();
    }
	
	public static PropositionManager propositionManager() {
        return PropositionManagerImpl.getInstance();
    }

	public static ThemeManager themeManager() {
        return ThemeManagerImpl.getInstance();
	}
	
	public static UtilisateurManager utilisateurManager() {
        return UtilisateurManagerImpl.getInstance();
    }

	public static TestManager testManager() {
        return TestManagerImpl.getInstance();
	}

	public static SectionTestManager sectionThemeManager() {
        return SectionTestManagerImpl.getInstance();
	}
	
	public static EpreuveManager epreuveManager() {
        return EpreuveManagerImpl.getInstance();
	}

	public static QuestionTirageManager questionTirageManager() {
		return QuestionTirageManagerImpl.getInstance();
	}
	
	public static PromotionManager promotionManager() {
		return PromotionManagerImpl.getInstance();
	}

	public static ReponseTirageManager reponseTirageManager() {
		return ReponseTirageManagerImpl.getInstance();
	}
	
	public static ProfilManager profilManager() {
		return ProfilManagerImpl.getInstance();
	}
	
}
